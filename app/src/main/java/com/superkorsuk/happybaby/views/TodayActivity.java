package com.superkorsuk.happybaby.views;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.fragments.BabyDoFragment;
import com.superkorsuk.happybaby.fragments.BaseFragment;
import com.superkorsuk.happybaby.fragments.GrowthFragment;
import com.superkorsuk.happybaby.fragments.StatsFragment;
import com.superkorsuk.happybaby.fragments.TodayFragment;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.util.BabyUtil;
import com.superkorsuk.happybaby.util.ProfileImageUtil;
import com.superkorsuk.happybaby.util.ShareUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

import static android.R.attr.data;
import static android.R.attr.start;


public class TodayActivity extends AppCompatActivity
        implements BaseFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    private static final int GET_PHOTO_FROM_GALLERY = 0;
    private static final int GET_PHOTO_FROM_CAMERA = 1;
    private static final int CROP_IMAGE = 2;

    private static final String ALBAT_STUDIO_EMAIL = "albat.studio@gmail.com";

    BottomBar bottomBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    Baby currentBaby;
    Uri imagePath;
    List<Baby> subBabies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_today);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                setupBabies();
                setupNavigationView();
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupBabies();
        setupNavigationView();

        createBottomBarListeners(savedInstanceState);
    }

    private void setupBabies() {
        subBabies.clear();
        int mainBabyId = BabyUtil.getCurrentBabyId(getSharedPreferences("status", MODE_PRIVATE));
        List<Baby> allBabies = new BabyRepository(this).all();
        for (Baby baby:
                allBabies) {
            if (baby.getId() == mainBabyId) {
                currentBaby = baby;
            } else {
                subBabies.add(baby);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case GET_PHOTO_FROM_GALLERY :
                imagePath = data.getData();
                //break 없음

            case GET_PHOTO_FROM_CAMERA:
                Intent intent1 = new Intent("com.android.camera.action.CROP");
                intent1.setDataAndType(imagePath, "image/*");
                intent1.putExtra("outputX", 200);
                intent1.putExtra("outputY", 200);
                intent1.putExtra("aspectX", 1);
                intent1.putExtra("aspectY", 1);
                intent1.putExtra("scale", true);
                intent1.putExtra("return-data", true);
                startActivityForResult(intent1, CROP_IMAGE);
                break;

            case CROP_IMAGE:
                Bundle extras = data.getExtras();
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/happyBaby/profile_" + currentBaby.getId() +".jpg";
//                        + System.currentTimeMillis() + ".jpg";

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ((ImageView)navigationView.findViewWithTag(-1)).setImageDrawable(makeImageCircleMask(photo));
                    ProfileImageUtil.storeImageFile(this, photo, filePath);
                }

                break;
            default:
                break;
        }
    }

    private void setupNavigationView() {
        Log.d("TEST", "Current Baby id : " + currentBaby.getId());

        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_baby_title)).setText(currentBaby.getName());
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_baby_subtitle)).setText(currentBaby.getBirthDayToString());


        // Current Profile ImageView
        ImageView imageViewProfile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView_Profile);
        imageViewProfile.setTag(-1);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(TodayActivity.this);
                CharSequence[] items = {"Gallery", "Camera"};
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch(i) {
                                    case 0:
                                        // get from gallery
                                        Intent intent = new Intent(Intent.ACTION_PICK);
                                        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                                        startActivityForResult(intent, GET_PHOTO_FROM_GALLERY);
                                        break;
                                    case 1:
                                        // get from camera
                                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        String url = "tmp_" + System.currentTimeMillis() + ".jpg";
                                        imagePath = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), url));
                                        intent1.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                                        startActivityForResult(intent1, GET_PHOTO_FROM_CAMERA);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("TEST", "canceled");
                            }
                        });
                dialog.show();
            }
        });

        setupProfileImage(imageViewProfile, currentBaby.getId());

        // Sub babies profile ImageView
        ImageView imageViewSubProfile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView_subProfile);
        imageViewSubProfile.setTag(0);
        ImageView imageViewSubProfile2 = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView_subProfile2);
        imageViewSubProfile2.setTag(1);

        View.OnClickListener createBabyListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawers();
                Intent intentNewBaby = new Intent(TodayActivity.this, BabyAddActivity.class);
                startActivity(intentNewBaby);
            }
        };

        View.OnClickListener currentBabyChangeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // switch babies
                Log.d("TEST", "Switch baby");
                Baby temp = new Baby();
                temp = currentBaby;
                currentBaby = subBabies.get((int)view.getTag());
                subBabies.set((int)view.getTag(), temp);

                BabyUtil.setCurrentBabyid(TodayActivity.this, (int) currentBaby.getId());

                setupNavigationView();
            }
        };

        switch (subBabies.size()) {
            case 0 :
                imageViewSubProfile.setImageResource(R.drawable.circle_plus);
                imageViewSubProfile.setOnClickListener(createBabyListener);
                break;
            case 1 :
                setupProfileImage(imageViewSubProfile, subBabies.get(0).getId());
                imageViewSubProfile.setOnClickListener(currentBabyChangeListener);
                imageViewSubProfile2.setImageResource(R.drawable.circle_plus);
                imageViewSubProfile2.setOnClickListener(createBabyListener);
                break;
            case 2 :
                setupProfileImage(imageViewSubProfile, subBabies.get(0).getId());
                imageViewSubProfile.setOnClickListener(currentBabyChangeListener);
                setupProfileImage(imageViewSubProfile2, subBabies.get(1).getId());
                imageViewSubProfile2.setOnClickListener(currentBabyChangeListener);
                break;
            default:
                break;

        }
    }

    private void setupProfileImage(ImageView toSetImageView, long babyId) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/happyBaby/profile_" + babyId + ".jpg");
        Bitmap storedBitmap;
        if (file.canRead()) {
            storedBitmap = BitmapFactory.decodeFile(String.valueOf(file));
        } else {
            storedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.com_facebook_profile_picture_blank_square);
        }
        toSetImageView.setImageDrawable(makeImageCircleMask(storedBitmap));
    }


    private RoundedBitmapDrawable makeImageCircleMask(Bitmap sourceBitmap) {
        RoundedBitmapDrawable roundedImage = RoundedBitmapDrawableFactory.create(getResources(), sourceBitmap);
        roundedImage.setCornerRadius(Math.max(sourceBitmap.getWidth(), sourceBitmap.getHeight()) / 2.0f);
        return roundedImage;
    }


    private void createBottomBarListeners(Bundle savedInstanceState) {

        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setDefaultTab(R.id.tab_today);


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                // TODO Fragment를 매번 생성하지 않도록 manager 생성 필요

                if (tabId == R.id.tab_today) {
                    switchTab(tabId);
                } else if (tabId == R.id.tab_baby_do) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_growth) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_statistics) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_dev) {
                    startActivity(new Intent(TodayActivity.this, MainActivity.class));
                }
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void switchTab(int tabId) {
        if (tabId == R.id.tab_today) {
            TodayFragment todayFrag = new TodayFragment();
            todayFrag.setBabyId(BabyUtil.getCurrentBabyId(getSharedPreferences("status", MODE_PRIVATE)));
            loadFragment(todayFrag);
            setTitle("Today");
        } else if (tabId == R.id.tab_baby_do) {
            loadFragment(new BabyDoFragment());
            setTitle("한일 입력");
        } else if (tabId == R.id.tab_growth) {
            loadFragment(new GrowthFragment());
            setTitle("아기 성장");
        } else if (tabId == R.id.tab_statistics) {
            loadFragment(new StatsFragment());
            setTitle("통계");
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_share:
                ShareUtil.sendShare(this);
                break;
            case R.id.nav_review:
                break;
            case R.id.nav_request_to_developer:
                drawer.closeDrawers();
                ShareUtil.sendMail(this, ALBAT_STUDIO_EMAIL);
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
