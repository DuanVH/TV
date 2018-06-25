package com.example.gem.tv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

/**
 * Created by gem on 6/25/18.
 */

public class CardPresenter extends Presenter {

  private static Context mContext;
  private static int CARD_WIDTH = 313;
  private static int CARD_HEIGHT = 176;

  static class ViewHolder extends Presenter.ViewHolder {

    private Movie mMovie;
    private ImageCardView mCardView;
    private Drawable mDefaultCardImage;
    private PicassoImageCardViewTarget mImageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      mCardView = (ImageCardView) view;
      mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
      mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.one_piece_film_z);
    }

    public void setMovie(Movie mMovie) {
      this.mMovie = mMovie;
    }

    public Movie getMovie() {
      return mMovie;
    }

    public ImageCardView getCardView() {
      return mCardView;
    }

    public Drawable getDefaultCardImage() {
      return mDefaultCardImage;
    }

    protected void updateCardViewImage(URI uri) {
      Picasso.with(mContext)
          .load(uri.toString())
          .resize(Utils.convertDpToPixel(mContext, CARD_WIDTH), Utils.convertDpToPixel(mContext, CARD_HEIGHT))
          .error(mDefaultCardImage)
          .into(mImageCardViewTarget);
    }

  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    mContext = parent.getContext();

    ImageCardView cardView = new ImageCardView(mContext);
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
    cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background));

    return new ViewHolder(cardView);
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {

    Movie movie = (Movie) item;
    ((ViewHolder) viewHolder).setMovie(movie);


    ((ViewHolder) viewHolder).mCardView.setTitleText(movie.getTitle());
    ((ViewHolder) viewHolder).mCardView.setContentText(movie.getStudio());
    ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);

    if (movie.getImageUrl() != null) {
      ((ViewHolder) viewHolder).updateCardViewImage(movie.getImageURI());
    } else {
      ((ViewHolder) viewHolder).mCardView.setMainImage(mContext.getResources().getDrawable(movie.getImage()));  // get Image in drawable

      //    ((ViewHolder) viewHolder).mCardView.setMainImage(((ViewHolder)viewHolder).getDefaultCardImage());  // Lay ra anh mac dinh cua ViewHolder
    }

  }

  @Override
  public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

  }

  @Override
  public void onViewAttachedToWindow(Presenter.ViewHolder holder) {
    super.onViewAttachedToWindow(holder);
  }

  public static class PicassoImageCardViewTarget implements Target {

    private ImageCardView mImageCardView;

    public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
      this.mImageCardView = mImageCardView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
      Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
      mImageCardView.setMainImage(bitmapDrawable);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
      mImageCardView.setMainImage(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
  }
}
