package com.mute.winter.core.modules.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.mute.winter.core.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;


/**
 * @author dkoller
 * @since 03.04.2017
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutView aboutView = AboutBuilder.with(this)
                .setPhoto(R.drawable.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName(getResources().getString(R.string.about_name))
                .setSubTitle(getResources().getString(R.string.about_job))
                .setBrief(getResources().getString(R.string.about_brief))
                .setDividerDashGap(5)

                .setLinksColumnsCount(4)
                .addGooglePlayStoreLink(getResources().getString(R.string.about_playstore))
                .addGitHubLink(getResources().getString(R.string.about_github))
                .addBitbucketLink(getResources().getString(R.string.about_bitbucket))
                //.addFacebookLink(getResources().getString(R.string.about_facebook))
                //.addTwitterLink(getResources().getString(R.string.about_twitter))
                //.addInstagramLink(getResources().getString(R.string.about_instagram))
                //.addGooglePlusLink(getResources().getString(R.string.about_google_plus))
                //.addYoutubeChannelLink(getResources().getString(R.string.about_youtube))
                //.addDribbbleLink(getResources().getString(R.string.about_dribble))
                //.addLinkedInLink(getResources().getString(R.string.about_linkedin))
                //.addEmailLink(getResources().getString(R.string.about_email))
                //.addWhatsappLink(getResources().getString(R.string.about_whatsapp), getResources().getString(R.string.about_whatsapp))
                //.addSkypeLink(getResources().getString(R.string.about_skype))
                //.addGoogleLink(getResources().getString(R.string.about_google))
                //.addAndroidLink(getResources().getString(R.string.about_android))
                //.addWebsiteLink(getResources().getString(R.string.about_website))

                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setVersionNameAsAppSubTitle()

                .setActionsColumnsCount(2)
                //.addFiveStarsAction()
                //.addMoreFromMeAction("")
                //.addShareAction(R.string.app_name)
                //.addUpdateAction()
                .addFeedbackAction(getResources().getString(R.string.about_feedback_email))
                //.addIntroduceAction((Intent) null)
                //.addHelpAction((Intent) null)
                //.addChangeLogAction((Intent) null)
                //.addRemoveAdsAction((Intent) null)
                //.addDonateAction((Intent) null)

                .setWrapScrollView(true)
                .setShowAsCard(true)
                .build();

        LinearLayout root = findViewById(R.id.root);
        root.addView(aboutView);
    }
}
