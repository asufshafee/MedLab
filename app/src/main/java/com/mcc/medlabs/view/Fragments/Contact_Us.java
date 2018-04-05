package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact_Us extends Fragment {


    public Contact_Us() {
        // Required empty public constructor
    }


    MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact__us, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Contact Us");
        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Contect_Us));

        }

        ((MainActivity) getActivity()).ShowBack_toolbar();

        view.findViewById(R.id.AboutUS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = About_Us.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "About Us").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

            }
        });

        view.findViewById(R.id.Email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("manag@medlabs.index.com.jo");

            }
        });

        view.findViewById(R.id.Call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "+96264631525"));
                startActivity(callIntent);
            }
        });
        view.findViewById(R.id.Facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenFacebookIntent(getActivity());
            }
        });
        view.findViewById(R.id.Linked).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenLinkedInIntent(getActivity());
            }
        });
        view.findViewById(R.id.Twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenTwitterIntent(getActivity());
            }
        });
        view.findViewById(R.id.Instagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenInstagramIntent(getActivity());
            }
        });
        view.findViewById(R.id.Youtube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenYouTubeIntent(getActivity());
            }
        });

        return view;
    }

    public static void getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/205714349528414")));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MedLabs")));
        }
    }

    public static void getOpenTwitterIntent(Context context) {

        Intent intent = null;
        try {
            // get the Twitter app if possible
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=1336304190"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MedLabsGroup"));
        }
        context.startActivity(intent);

    }


    public static void getOpenYouTubeIntent(Context context) {
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCpFEodAfx8lmFCPjJmx8sAg"));

            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse("https://www.youtube.com/channel/UCpFEodAfx8lmFCPjJmx8sAg"));
            PackageManager manager = context.getPackageManager();
            List<ResolveInfo> infos = manager.queryIntentActivities(intent, 0);
            if (infos.size() > 0) {
            } else {
                //No Application can handle your intent
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/MedLabsGroup"));
            }
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/MedLabsGroup"));
        }
        context.startActivity(intent);
    }

    public static void getOpenInstagramIntent(Context context) {


        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://instagram.com/_u/" + "medlabs"));
            intent.setPackage("com.instagram.android");
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/" + "medlabs/")));
        }


//        Intent intent = null;
//        try {
//            intent = context.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
//            intent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
//            intent.setData(Uri.parse("http://instagram.com/_u/medlabs/"));
//        } catch (Exception e) {
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/medlabs/"));
//        }
//        context.startActivity(intent);
    }

    public static void getOpenLinkedInIntent(Context context) {

        Intent intent = null;
        intent = new Intent(Intent.ACTION_VIEW);

        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://company/5234235"));
            final PackageManager packageManager = context.getPackageManager();
            final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.isEmpty()) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://linkd.in/1IUj3DY"));
            }
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://linkd.in/1IUj3DY"));
        }
        context.startActivity(intent);

    }

    public void sendEmail(String email) {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("message/rfc822");
        String[] arrayOfString = new String[1];
        arrayOfString[0] = email;
        localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString);
        localIntent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name_share));
        localIntent.putExtra("android.intent.extra.TEXT", "");
        try {
            startActivity(localIntent);
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            EasyToast.error(getActivity(), "There are no email clients installed.");

        }

    }

}
