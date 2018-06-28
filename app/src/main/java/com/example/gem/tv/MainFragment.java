package com.example.gem.tv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gem on 6/25/18.
 */

public class MainFragment extends BrowseFragment {

  private static final int DRAWABLE = 0;
  private static final int PICASSO = 1;

  private static final int GRID_ITEM_HEIGHT = 100;
  private static final int GRID_ITEM_WIDTH = 200;

  private static final String TAG = MainFragment.class.getSimpleName();

  private static SimpleBackgroundManager simpleBackgroundManager = null;
  private static PicassoBackgroundManager picassoBackgroundManager = null;

  private ArrayObjectAdapter mRowsAdapter;


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    simpleBackgroundManager = new SimpleBackgroundManager(getActivity());
    picassoBackgroundManager = new PicassoBackgroundManager(getActivity());

    setupUIElements();
    loadRows();
    setupEventListener();
  }

  private void setupEventListener() {
    setOnItemViewSelectedListener(new ItemViewSelectedListener());
    setOnItemViewClickedListener(new ItemViewClickedListener());
  }

  private final class ItemViewSelectedListener implements OnItemViewSelectedListener {

    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

      int test = PICASSO;

      if (test == DRAWABLE) {
        switch ((int) row.getId()) {
          case 0:
            simpleBackgroundManager.clearBackground();
            break;

          case 1:
            simpleBackgroundManager.updateBackground(getActivity().getDrawable(R.drawable.background_second));
            break;

          case 2:
            simpleBackgroundManager.updateBackground(getActivity().getDrawable(R.drawable.background_third));
            break;

          case 3:
            simpleBackgroundManager.updateBackground(getActivity().getDrawable(R.drawable.background_fourth));
            break;

          default:
            break;
        }
      } else if (test == PICASSO) {
        switch ((int) row.getId()) {
          case 0:
            picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/10/RIMG0656.jpg");
            break;

          case 1:
            picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");
            break;

          case 2:
            picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02630.jpg");
            break;

          case 3:
            picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02529.jpg");
            break;

          default:
            break;
        }
      } else {
        if (item instanceof String) {
          // GridItemPresenter
          picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/10/RIMG0656.jpg");
        } else {
          // CardPresenter
          picassoBackgroundManager.updateBackgroundWithDelay("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");

        }
      }
    }
  }

  private final class ItemViewClickedListener implements OnItemViewClickedListener {

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

    }
  }

  private void loadRows() {
    mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

    HeaderItem gridItemPresenterHeader = new HeaderItem(0, "GridItemPresenter");
    GridItemPresenter mGridPresenter = new GridItemPresenter();
    ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
    gridRowAdapter.add("ITEM 1");
    gridRowAdapter.add("ITEM 2");
    gridRowAdapter.add("ITEM 3");
    gridRowAdapter.add("ITEM 4");
    mRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));

    HeaderItem headerItem1 = new HeaderItem(1, "Duan");
    GridItemPresenter itemPresenter = new GridItemPresenter();
    ArrayObjectAdapter objectAdapter = new ArrayObjectAdapter(itemPresenter);
    objectAdapter.add("ITEM 1");
    objectAdapter.add("ITEM 2");
    objectAdapter.add("ITEM 3");
    objectAdapter.add("ITEM 4");
    mRowsAdapter.add(new ListRow(headerItem1, objectAdapter));

    HeaderItem headerItemCardView = new HeaderItem(2, "One Piece");
    CardPresenter cardPresenter = new CardPresenter();
    ArrayObjectAdapter cardViewAdapter = new ArrayObjectAdapter(cardPresenter);
    cardViewAdapter.add(new Movie(1, "Luffy", "One Piece", R.drawable.luffy));
    cardViewAdapter.add(new Movie(2, "Zoro", "One Piece", R.drawable.zoro));
    cardViewAdapter.add(new Movie(3, "Usopp", "One Piece", R.drawable.usopp));
    cardViewAdapter.add(new Movie(4, "Nami", "One Piece", R.drawable.nami));
    cardViewAdapter.add(new Movie(5, "Franky", "One Piece", R.drawable.franky));
    cardViewAdapter.add(new Movie(6, "Sanji", "One Piece", R.drawable.sanji));
    cardViewAdapter.add(new Movie(7, "Chopper", "One Piece", R.drawable.chopper));
    cardViewAdapter.add(new Movie(8, "Brook", "One Piece", R.drawable.brook));
    cardViewAdapter.add(new Movie(9, "Robin", "One Piece", R.drawable.robin));
    mRowsAdapter.add(new ListRow(headerItemCardView, cardViewAdapter));


    HeaderItem headerItemCardViewPicasso = new HeaderItem(3, "Picasso");
    CardPresenter cardPresenterPicasso = new CardPresenter();
    ArrayObjectAdapter objectAdapterpicasso = new ArrayObjectAdapter(cardPresenterPicasso);
    objectAdapterpicasso.add(new Movie("Luffy", "One Piece", "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png"));
    objectAdapterpicasso.add(new Movie("Zoro", "One Piece", "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png/revision/latest?cb=20151110155131"));
    objectAdapterpicasso.add(new Movie("Usopp", "One Piece", "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png/revision/latest?cb=20141206174410"));
    objectAdapterpicasso.add(new Movie("Nami", "One Piece", "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg"));
    mRowsAdapter.add(new ListRow(headerItemCardViewPicasso, objectAdapterpicasso));

    setAdapter(mRowsAdapter);
  }


  private void setupUIElements() {
    // setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
    setTitle("Hello Android TV!"); // Badge, when set, takes precedent
    // over title
    setHeadersState(HEADERS_ENABLED);
    setHeadersTransitionOnBackEnabled(true);

    // set fastLane (or headers) background color
    setBrandColor(getResources().getColor(R.color.fastlane_background));
    // set search icon color
    setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
  }

  private class GridItemPresenter extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
      TextView view = new TextView(parent.getContext());
      view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
      view.setFocusable(true);
      view.setFocusableInTouchMode(true);
      view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
      view.setTextColor(Color.WHITE);
      view.setGravity(Gravity.CENTER);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
      ((TextView) viewHolder.view).setText((String) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
  }

}
