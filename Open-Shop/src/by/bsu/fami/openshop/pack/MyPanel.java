package by.bsu.fami.openshop.pack;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = -6938308174884009354L;

    private Thread thead = null;

    private ArrayList<ArrayList<Work>> mashwork = null;
    private ArrayList<ArrayList<Work>> record = null;

    private int n, m;
    private int a[][] = new int[10][10];
    private int miin = 999999;
    private int mass[] = new int[10];
    private int masost[] = new int[10];
    private boolean maszan[] = new boolean[10];
    private boolean masbool[][] = new boolean[10][10];
    private int massstack[] = new int[10];
    private BufferedImage bufferimage = null;
    private int SHIFT = 30;
    private int WIDTHW = 50;
    private int HEIGHTW = 10;
    private int coof = 8;
    static private int timedelay = 1000;
    private boolean perebor = true;
    private boolean running = false;
    private boolean correct = true;
    private int numberRecord = 0;
    private Records records = null;

    public final static int SEARCH = 0;
    public final static int APPROXIMATE = 1;

    private int styleAnswer = 0;
    private Color colors[] = { Color.BLUE, Color.CYAN, Color.GREEN,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };
    private Color colorsLight[];

    public MyPanel(Resh ff, String filename, int t, boolean per, int style) {
        miin = 999999;
        perebor = per;
        timedelay = t;

        styleAnswer = style;
        records = new Records();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                s = br.readLine();
                StringTokenizer stt = new StringTokenizer(s);
                for (int j = 0; j < m; j++) {
                    a[i][j] = Integer.parseInt(stt.nextToken());
                }
            }
            initColors();
            initialization();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sorry incorrect data",
                    "Incorrect data", JOptionPane.ERROR_MESSAGE);
            correct = false;
        }
        repaint();
    }

    public MyPanel(Resh ff, int t, boolean per, int nn, int mm, int aa[][],
            int style) {
        miin = 999999;
        perebor = per;
        timedelay = t;
        records = new Records();
        n = nn;
        m = mm;
        a = aa;
        styleAnswer = style;
        initColors();
        initialization();
    }

    private void initColors() {
        colorsLight = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorsLight[i] = new Color(colors[i].getRed(),
                    colors[i].getGreen(), colors[i].getBlue(), colors[i]
                            .getTransparency() + 50);
        }
    }

    private void initialization() {
        for (int i = 0; i < m; i++) {
            masost[i] = 0;
            massstack[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                masost[i] += a[j][i];
            }
        }
        setLayout(null);
        setLayout(new GridLayout(4, m));
        mashwork = new ArrayList<ArrayList<Work>>();
        for (int i = 0; i < m; i++) {
            mashwork.add(new ArrayList<Work>());
        }

        thead = new Thread(this);
        thead.start();

    }

    @SuppressWarnings("static-access")
    private void findAnswerRoughly() {
        int mn = findMin();
        if (mn != (-1)) {
            if (massstack[mn] != -1) {
                int k = massstack[mn];
                maszan[k] = false;
            }
            int j = findminWork(mn);
            if (j != -1) {
                mashwork.get(mn).add(new Work(mass[mn], a[j][mn], j));
                mass[mn] += a[j][mn];
                masost[mn] -= a[j][mn];
                masbool[j][mn] = true;
                maszan[j] = true;
                massstack[mn] = j;
                if (perebor) {
                    repaint();
                    try {
                        thead.sleep(timedelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                findAnswerRoughly();
                return;
            } else {
                int kk = findNext(mass[mn], mn);
                for (int i = 0; i < m; i++)
                    if ((mass[i] == mass[kk]) && (i != mn)
                            && (massstack[i] != -1)
                            && (!masbool[massstack[i]][mn])) {
                        int tk = 0;
                        if (mass[kk] > mass[mn]) {
                            mass[mn] = mass[kk] + a[massstack[i]][mn];
                            tk = mass[kk];
                        } else {
                            tk = mass[mn];
                            mass[mn] += a[massstack[i]][mn];
                        }
                        mashwork.get(mn)
                                .add(
                                        new Work(tk, a[massstack[i]][mn],
                                                massstack[i]));
                        masbool[massstack[i]][mn] = true;
                        masost[mn] -= a[massstack[i]][mn];
                        massstack[mn] = massstack[i];
                        massstack[i] = -1;
                        if (perebor) {
                            repaint();
                            try {
                                thead.sleep(timedelay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        findAnswerRoughly();
                        return;
                    }
            }
        } else {

            int mx = findMax();

            if (mx < miin) {
                if (perebor) {
                    repaint();
                    try {
                        thead.sleep(timedelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                miin = mx;

                records.clear();
                records.add(mashwork);
            }

        }
    }

    private void findAnswerSearch() {
        boolean prov = false;
        int mnn = findMin();
        if (mnn != -1) {
            for (int mn = 0; mn < m; mn++) {

                if ((mass[mn] == mass[mnn]) && (masost[mn] != 0)) {

                    if (massstack[mn] != -1) {
                        int k = massstack[mn];
                        maszan[k] = false;
                    }
                    for (int i = 0; i < n; i++) {
                        if ((!masbool[i][mn]) && (!maszan[i])) {
                            if (mass[mn] + masost[mn] > miin) {
                                return;
                            } else {
                                prov = true;
                                mashwork.get(mn).add(
                                        new Work(mass[mn], a[i][mn], i));
                                mass[mn] += a[i][mn];
                                masost[mn] -= a[i][mn];
                                masbool[i][mn] = true;
                                maszan[i] = true;
                                int t = massstack[mn];
                                massstack[mn] = i;
                                if (perebor) {
                                    repaint();
                                    try {
                                        Thread.sleep(timedelay);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                findAnswerSearch();
                                mashwork.get(mn).remove(
                                        mashwork.get(mn).size() - 1);
                                maszan[i] = false;
                                mass[mn] -= a[i][mn];
                                masost[mn] += a[i][mn];
                                massstack[mn] = t;
                                masbool[i][mn] = false;
                            }
                        }
                    }
                    if (!prov) {
                        int kk = findNext(mass[mn], mn);
                        if (kk == -1)
                            return;
                        for (int i = 0; i < m; i++)
                            if ((mass[i] == mass[kk]) && (i != mn)
                                    && (massstack[i] != -1)
                                    && (!masbool[massstack[i]][mn])) {
                                int t = mass[mn];
                                int tk = 0;
                                if (mass[kk] + masost[mn] <= miin) {
                                    if (mass[kk] > mass[mn]) {
                                        mass[mn] = mass[kk]
                                                + a[massstack[i]][mn];
                                        tk = mass[kk];
                                    } else {
                                        tk = mass[mn];
                                        mass[mn] += a[massstack[i]][mn];
                                    }
                                    mashwork.get(mn).add(
                                            new Work(tk, a[massstack[i]][mn],
                                                    massstack[i]));
                                    masbool[massstack[i]][mn] = true;
                                    masost[mn] -= a[massstack[i]][mn];
                                    int ttt = massstack[mn];
                                    massstack[mn] = massstack[i];
                                    massstack[i] = -1;
                                    if (perebor) {
                                        repaint();
                                        try {
                                            Thread.sleep(timedelay);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    findAnswerSearch();
                                    mashwork.get(mn).remove(
                                            mashwork.get(mn).size() - 1);
                                    massstack[i] = massstack[mn];
                                    massstack[mn] = ttt;
                                    masost[mn] += a[massstack[i]][mn];
                                    mass[mn] = t;
                                    masbool[massstack[i]][mn] = false;
                                }// else{
                                // return;
                                // }
                            }
                    }
                    if (massstack[mn] != -1) {
                        int k = massstack[mn];
                        maszan[k] = true;
                    }

                }
            }
        } else {
            int mx = findMax();

            if (mx == miin) {
                if (perebor) {
                    repaint();
                    try {
                        Thread.sleep(timedelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                record = new ArrayList<ArrayList<Work>>();
                for (int i = 0; i < m; i++) {
                    record.add(new ArrayList<Work>());
                }
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < mashwork.get(i).size(); j++) {
                        record.get(i).add(mashwork.get(i).get(j));
                    }
                }
                if (!records.contains(record)) {
                    records.add(record);
                }
            }

            if (mx < miin) {
                if (perebor) {
                    repaint();
                    try {
                        Thread.sleep(timedelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                miin = mx;
                record = new ArrayList<ArrayList<Work>>();
                for (int i = 0; i < m; i++) {
                    record.add(new ArrayList<Work>());
                }
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < mashwork.get(i).size(); j++) {
                        record.get(i).add(mashwork.get(i).get(j));
                    }
                }
                records.clear();
                records.add(record);
            }
        }
    }

    private int findMin() {
        int index = -1;
        int minn = 999999;
        for (int i = 0; i < m; i++)
            if ((mass[i] < minn) && (masost[i] != 0)) {
                minn = mass[i];
                index = i;
            }
        return index;
    }

    private int findMax() {

        int max = mass[0];
        for (int i = 0; i < m; i++)
            if (mass[i] > max) {
                max = mass[i];
            }
        return max;
    }

    private int findminWork(int k) {
        int index = -1;
        int minn = 999999;
        for (int i = 0; i < n; i++)
            if ((a[i][k] < minn) && (!masbool[i][k]) && (!maszan[i])) {
                minn = a[i][k];
                index = i;
            }
        return index;
    }

    private int findNext(int x, int num) {
        int ind = -1;
        int minn = 99999;
        for (int i = 0; i < m; i++)
            if (((minn > mass[i])) && (i != num)) {
                if ((massstack[i] != (-1)) && (!masbool[massstack[i]][num])) {
                    minn = mass[i];
                    ind = i;
                }
            }
        return ind;
    }

    public void setdelay(int t) {
        timedelay = t;
    }

    public void run() {
        running = true;
        if (styleAnswer == MyPanel.APPROXIMATE) {
            findAnswerRoughly();
        } else {
            findAnswerSearch();
        }
        running = false;
        repaint();
    }

    public boolean isRunning() {
        return running;
    }

    public void paint(Graphics g) {
        super.paint(g);
        int h = getHeight();
        int w = getWidth();
        bufferimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics gg = bufferimage.getGraphics();
        if (!isFree(mashwork)) {
            drawWork(mashwork, gg);
        } else {
            if (records.length() != 0) {
                drawWork(records.get(numberRecord), gg);
            }
        }
        g.drawImage(bufferimage, 0, 0, null);
    }

    private boolean isFree(ArrayList<ArrayList<Work>> wk) {
        if (wk == null)
            return false;
        for (int i = 0; i < wk.size(); i++) {
            if (wk.get(i).size() > 0) {
                return false;
            }
        }
        return true;
    }

    private void draw3DRect(int i, int j, Work work, Graphics gg) {
        int h = getHeight();
        int rect1x[] = new int[4];
        int rect1y[] = new int[4];
        int rect2x[] = new int[4];
        int rect2y[] = new int[4];
        rect1x[0] = SHIFT + i * WIDTHW;
        rect1x[1] = SHIFT + i * WIDTHW + WIDTHW / 4;
        rect1x[2] = SHIFT + (i + 1) * WIDTHW + WIDTHW / 4;
        rect1x[3] = SHIFT + (i + 1) * WIDTHW;
        rect1y[0] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW;
        rect1y[1] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW - WIDTHW / 4;
        rect1y[2] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW - WIDTHW / 4;
        rect1y[3] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW;
        rect2x[0] = SHIFT + (i + 1) * WIDTHW;
        rect2x[1] = SHIFT + (i + 1) * WIDTHW;
        rect2x[2] = SHIFT + (i + 1) * WIDTHW + WIDTHW / 4;
        rect2x[3] = SHIFT + (i + 1) * WIDTHW + WIDTHW / 4;
        rect2y[0] = h - SHIFT - work.getBeginTime() * HEIGHTW;
        rect2y[1] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW;
        rect2y[2] = h - SHIFT - work.getBeginTime() * HEIGHTW
                - work.getWorkTime() * HEIGHTW - WIDTHW / 4;
        rect2y[3] = h - SHIFT - work.getBeginTime() * HEIGHTW - WIDTHW / 4;

        Polygon p1 = new Polygon(rect1x, rect1y, 4);
        Polygon p2 = new Polygon(rect2x, rect2y, 4);
        gg.setColor(colorsLight[i%colorsLight.length]);
        gg.fillPolygon(p1);
        gg.fillPolygon(p2);
        gg.setColor(colors[i%colors.length]);
        gg.fillRect(SHIFT + i * WIDTHW, h - SHIFT
                - (work.getBeginTime() + work.getWorkTime()) * HEIGHTW, WIDTHW,
                work.getWorkTime() * HEIGHTW);
        gg.setColor(Color.BLACK);
        gg.drawRect(SHIFT + i * WIDTHW, h - SHIFT
                - (work.getBeginTime() + work.getWorkTime()) * HEIGHTW, WIDTHW,
                work.getWorkTime() * HEIGHTW);
        gg.drawPolygon(p1);
        gg.drawPolygon(p2);
        gg.drawString((work.getWork() + 1) + " ", SHIFT + i * WIDTHW + WIDTHW
                / 2, h - SHIFT - HEIGHTW * work.getBeginTime()
                - work.getWorkTime() * HEIGHTW / 2);
    }

    private void drawWork(ArrayList<ArrayList<Work>> wk, Graphics gg) {
        int h = getHeight();
        int w = getWidth();
        HEIGHTW = (h - SHIFT) / (coof * 5);
        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, w, h);
        gg.setColor(Color.BLACK);
        gg.drawLine(SHIFT, h - SHIFT, w, h - SHIFT);
        gg.drawLine(SHIFT, h - SHIFT, SHIFT, 0);
        if (correct) {
            for (int i = 0; i < wk.size(); i++) {
                for (int j = 0; j < wk.get(i).size(); j++) {
                    gg.setColor(Color.YELLOW);
                    if (wk.get(i).get(j).getBeginTime()
                            + wk.get(i).get(j).getWorkTime() > coof * 5) {
                        coof = (wk.get(i).get(j).getBeginTime() + wk.get(i)
                                .get(j).getWorkTime()) / 5 + 1;
                        repaint();
                        return;
                    }
                    draw3DRect(i, j, wk.get(i).get(j), gg);
                }
            }
        }
        if (miin != 999999) {
            gg.drawString("Min time-" + miin, w - 2 * WIDTHW, WIDTHW);
        } else {
            gg.drawString("Min time-?", w - 2 * WIDTHW, WIDTHW);
        }
        if (styleAnswer == MyPanel.SEARCH) {
            gg.drawString("Number of records-" + records.length(), w - 3
                    * WIDTHW, WIDTHW + 20);
        }
        if (running) {
            gg.drawString("Running", w - 3 * WIDTHW, WIDTHW + 40);
        }
        for (int i = 1; i <= coof; i++) {
            gg.drawLine(SHIFT - 5, h - SHIFT - i * HEIGHTW * 5, SHIFT, h
                    - SHIFT - i * HEIGHTW * 5);
            gg
                    .drawString("" + (i * 5), SHIFT - 25, h - SHIFT - i
                            * HEIGHTW * 5);
        }
    }

    @SuppressWarnings("deprecation")
    public void stopp() {
        if (thead != null) {
            thead.stop();
        }
    }

    public void nextRecord() {
        if ((numberRecord + 1) < records.length()) {
            numberRecord++;
        }
        repaint();
    }

    public void prevRecord() {
        if (numberRecord > 0) {
            numberRecord--;
        }
        repaint();
    }

    public void saveresualt(String file) {
        int h = getHeight();
        int w = getWidth();
        bufferimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics gg = bufferimage.getGraphics();
        if (!isFree(mashwork)) {
            drawWork(mashwork, gg);
        } else {
            if (records.length() != 0) {
                drawWork(records.get(numberRecord), gg);
            }
        }
        try {
            ImageIO.write(bufferimage, "jpeg", new File(file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}