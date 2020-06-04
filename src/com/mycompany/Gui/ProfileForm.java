package com.mycompany.Gui;

import com.codename1.capture.Capture;
import com.codename1.io.Util;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import statistiques.EventPieChart;

/**
 * The user profile form
 *
 */
public class ProfileForm extends BaseForm {

    public Label findPhoto;

    public Form StatEvent(Resources res) {
        EventPieChart a = new EventPieChart();
        Form stats_Form = a.execute();
        SpanLabel test_SpanLabel = new SpanLabel("Hiiii");
        stats_Form.getToolbar().addCommandToRightBar("back", null, evt -> {
            new HomeMembre(res).show();
        });
        Class cls = EventPieChart.class;

        return stats_Form;
    }

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                        )
                )
        ));

        Button downloadPdf = new Button("PDF  des Dons et des Stocks");
        downloadPdf.getAllStyles().setBorder(Border.createRoundBorder(15, 12));

        add(downloadPdf);
        downloadPdf.addActionListener((evt) -> {
            String pdf = "http://localhost/PI9/web/app_dev.php/dashboard/pdf";
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "pdf-sample.pdf";

            Util.downloadUrlToFile(pdf, fileName, true);
            Display.getInstance().execute(fileName);
        });

        Button stat = new Button("Stat");
        stat.getAllStyles().setBorder(Border.createRoundBorder(15, 12));
        add(stat);
        stat.addActionListener((evt) -> {
            StatEvent(res).show();
        });

        Button capture = new Button("capture");
        capture.getAllStyles().setBorder(Border.createRoundBorder(15, 12));
        add(capture);
        stat.addActionListener((evt) -> {
            String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i != null) {

            }
        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
