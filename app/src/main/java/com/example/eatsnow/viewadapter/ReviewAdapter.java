package com.example.eatsnow.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.eatsnow.BuildConfig;
import com.example.eatsnow.R;
import com.example.eatsnow.databinding.CardReviewBinding;
import com.example.eatsnow.model.ReviewModel;
import com.example.eatsnow.model.reviews.Review;
import com.example.eatsnow.model.reviews.ReviewResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ReviewModel reviewModel;
    private DisposableObserver<ReviewResponse> disposableObserver;
    private ReviewResponse reviewResponse;

    public ReviewAdapter(final String resId) {

        reviewModel = new ReviewModel(BuildConfig.ZOMATO_BASE_URL);

        disposableObserver = reviewModel.getReviews(resId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<ReviewResponse>() {
                @Override
                public void onNext(ReviewResponse response) {
                    reviewResponse = response;
                    notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ReviewViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.card_review,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return null == reviewResponse ? 0 : reviewResponse.getUserReviews().size();
    }

    public void clean() {

        if(null != disposableObserver && !disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private CardReviewBinding binder;

        ReviewViewHolder(CardReviewBinding binder) {
            super(binder.getRoot());
            this.binder = binder;
        }

        void onBind(int position) {

            Review review = reviewResponse.getUserReviews().get(position).getReview();

            binder.rbRating.setRating(((float) review.getRating()));
            binder.tvRatingText.setText(review.getRatingText());
            binder.tvReviewerName.setText(review.getUser().getName());
            binder.tvReviewDate.setText(review.getReviewTimeFriendly());
            binder.tvReviewContent.setText(review.getReviewText());
        }
    }
}
