
package neuralmapper.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.*;
import java.io.*;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.io.Serializable;
import neuralmapper.model.Network;

/* ScrollDemo2.java requires no other files. */
public class GUIControl extends JPanel
                         implements MouseListener,
                         Printable{
    private Dimension area; //indicates area taken up by graphics
    private Vector<Rectangle> GraphicObjects; //coordinates used to draw graphics
    private JComponent newContentPane;
    private JPanel drawingPane;
    private Point clickPoint;
    private Color rgb = new Color(255,0,0);

    private ArrayList<Color> colors = new ArrayList<Color>();
    
    private ArrayList<Integer> shapeList = new ArrayList<Integer>();
    private JMenuBar menuBar;
    
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem menuItem;
    
    private Boolean isHit = false;
    
    private Network networkClone;
    
    public GUIControl() {
        super(new BorderLayout());
        
        clickPoint = new Point(0,0);
        area = new Dimension(0,0);
        GraphicObjects = new Vector<>();
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        
        menuBar.add(Box.createHorizontalGlue());
        
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        
        menuItem = new JMenuItem("Load",KeyEvent.VK_L);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    loadActionPerformed(evt);
                } catch (IOException | ClassNotFoundException ex) {}
                
            }
        });
        fileMenu.add(menuItem);
        
        menuItem = new JMenuItem("Save",KeyEvent.VK_S);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveActionPerformed(evt);
                } catch (IOException ex) {
//                    Logger.getLogger(SelectionArea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        fileMenu.add(menuItem);
        
        menuItem = new JMenuItem("Print",KeyEvent.VK_P);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        fileMenu.add(menuItem);
        
        fileMenu.addSeparator();
        
        menuItem = new JMenuItem("Exit",KeyEvent.VK_X);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        menuItem.setToolTipText("Exit the Program");
        fileMenu.add(menuItem);
        
        menuItem = new JMenuItem("Help");
        menuItem.setMnemonic(KeyEvent.VK_Y);
        helpMenu.add(menuItem);
        
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(Color.white);
        drawingPane.addMouseListener(this);
        System.out.println("re initializing");

        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(200,200));
        
        JToolBar optionsPanel = new JToolBar();
        
        optionsPanel.setLayout(new GridLayout(10,1));
        
        ButtonGroup group1 = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        JRadioButton red = new JRadioButton("Red");
        JRadioButton green = new JRadioButton("Green");
        JRadioButton blue = new JRadioButton("Blue");
//        RectIcon rectIcon = new RectIcon(Color.BLACK);
//        RectIcon customRectIcon = new RectIcon(rgb);
//        OvalIcon ovalIcon = new OvalIcon();
//        LineIcon lineIcon = new LineIcon();
//        JButton customColorButton = new JButton(customRectIcon);
//        JButton rectangle = new JButton(rectIcon);
//        JButton oval = new JButton(ovalIcon);
//        JButton line = new JButton(lineIcon);
        JRadioButton fillButton = new JRadioButton("Fill");
        JRadioButton unfillButton = new JRadioButton("Unfilled");
        JToggleButton moveButton = new JToggleButton("Move Tool");
        
        red.setSelected(true);
        red.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redRadioActionPerformed(evt);
            }
        });
        
        green.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenRadioActionPerformed(evt);
            }
        });
        
        blue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueRadioActionPerformed(evt);
            }
        });
        
        
           
        
        group1.add(red);
        group1.add(green);
        group1.add(blue);
        
        group2.add(fillButton);
        group2.add(unfillButton);
        
//        group2.add(rectangle);
//        group2.add(oval);
//        group2.add(line);
        
//        optionsPanel.add(rectangle);
//        optionsPanel.add(oval);
//        optionsPanel.add(line);
//        optionsPanel.add(new JLabel(""));
        optionsPanel.addSeparator();
        optionsPanel.add(fillButton);
        optionsPanel.add(unfillButton);
//        optionsPanel.add(new JLabel(""));
        optionsPanel.addSeparator();
//        optionsPanel.add(customColorButton);
        optionsPanel.add(red);
        optionsPanel.add(green);
        optionsPanel.add(blue);
        optionsPanel.addSeparator();
        optionsPanel.add(moveButton);
        

        //Lay out this demo.
        add(optionsPanel, BorderLayout.LINE_START);
//        add(toolBar, BorderLayout.LINE_START);
        add(menuBar, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);
    }
    
    private JPopupMenu popup;
    
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        popup = new JPopupMenu();
        
        menuItem = new JMenuItem("Save and Quit");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveAndQuitActionPerformed(evt);
                } catch (IOException ex) {
//                    Logger.getLogger(SelectionArea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        popup.add(menuItem);
        
        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                justQuitActionPerformed(evt);
            }
        });
        popup.add(menuItem);
        
        menuItem = new JMenuItem("Cancel");
        popup.add(menuItem);
        
        popup.show(this,this.getX()+50,this.getY()+50);
        
    }
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {
        // must save:
        
        
        String s = (String)JOptionPane.showInputDialog(this,"File Name:","Save File",JOptionPane.PLAIN_MESSAGE,null,null,"file.tmp");
        FileOutputStream fos = new FileOutputStream(s);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(colors);
        oos.writeObject(GraphicObjects);
        oos.writeObject(shapeList);
        
        oos.close();
        // Color List
        // Shape List
        // Fill List
    }
    
    private void loadActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException, ClassNotFoundException {
        String s = (String)JOptionPane.showInputDialog(this,"File Name:","Load File",JOptionPane.PLAIN_MESSAGE,null,null,"file.tmp");
        FileInputStream fis = new FileInputStream(s);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        colors = (ArrayList)ois.readObject();
        GraphicObjects = (Vector)ois.readObject();
        shapeList = (ArrayList)ois.readObject();
        
        ois.close();
        
//        System.out.println(colors.get(1).toString());
//        System.out.println(shapeList.get(0).toString());
        
        drawingPane.repaint();
    }
    
    private void customColorActionPerformed(java.awt.event.ActionEvent evt) {
//        ColorChooserDemo cc = new ColorChooserDemo();
//        Color newColor = JColorChooser.showDialog(cc, "Choose Color", Color.RED);
//        rgb = newColor;
        
//        this.repaint();
    }
    
    private void saveAndQuitActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        saveActionPerformed(evt);
        System.exit(0);
    }
    
    private void justQuitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }
    
    
    private void redRadioActionPerformed(java.awt.event.ActionEvent evt) {                                         
        rgb = new Color(255,0,0);
    }
    
    private void greenRadioActionPerformed(java.awt.event.ActionEvent evt) {                                         
        rgb = new Color(0,255,0);
    }
    
    private void blueRadioActionPerformed(java.awt.event.ActionEvent evt) {                                         
        rgb = new Color(0,0,255);
    }
    
    private void printActionPerformed(java.awt.event.ActionEvent evt) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if(ok) {
            try {
                job.print();
            }
            catch(PrinterException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
//    private void clearActionPerformed(java.awt.event.ActionEvent evt) {
//        //This will clear the graphic objects.
//            GraphicObjects.removeAllElements();
//            area.width=0;
//            area.height=0;
//            changed = true;
//            colors.clear();
//            shapeList.clear();
//            fillList.clear();
//            
//             //Update client's preferred size because
//            //the area taken up by the graphics has
//            //gotten larger or smaller (if cleared).
//            drawingPane.setPreferredSize(area);
//
//            //Let the scroll pane know to update itself
//            //and its scrollbars.
//            drawingPane.revalidate();
//            
//            drawingPane.repaint();
//    }

    /** The component inside the scroll pane. */
    public class DrawingPane extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Rectangle rect;
//            System.out.println("GO SIZE: ");
            System.out.println(GraphicObjects.size());
            for(int i = 0; i < GraphicObjects.size(); i++) {
                rect = GraphicObjects.elementAt(i);
                g.setColor(Color.RED);
                g.drawOval(rect.x, rect.y, rect.width, rect.height);
            }
//            g.drawOval(0,0,50,50);
        }
    }

    //Handle mouse events.
    public void mouseReleased(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e){
        Point curLoc = e.getPoint();
        clickPoint = curLoc;
    }
    
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
//    public void mousePressed(MouseEvent e){}
    public void setNetBase(Network netClone){
        networkClone = netClone;
        
        int numIn = networkClone.getIn();
        int numDeepCol = networkClone.getDeepColumns();
        int numDeepRow = networkClone.getDeepRows();
        int numOut = networkClone.getOut();
        
        int inStart = 20;
        int deepStart = 20;
        int outStart = 20;
//        int mod = -20;
        
        int yOrigin = 0;
        
        // Change yOrigin for number of nodes
        int tallestNode = 0;
        if(numIn > tallestNode){
            tallestNode = numIn;
        }
        else if(numDeepRow > tallestNode){
            tallestNode = numDeepRow;
        }
        else if(numOut > tallestNode){
            tallestNode = numOut;
        }
        yOrigin = 30*(tallestNode + 1);
        
        // Change starting Y for even/odd number of nodes
        if(numIn % 2 == 0){
            inStart = 50;
        }
        
        if(numDeepRow % 2 == 0){
            deepStart = 50;
        }
        
        if(numOut % 2 == 0){
            outStart = 50;
        }
        
        int curCol = 0;
        int curRow = 0;
        int w = 40;
        int h = 40;
        int colSep = 20;
        int rowSep = 20;
        int inverter = -1;
        GraphicObjects.add(new Rectangle(0,yOrigin,400,1));
        
        Rectangle rect;
        // Paint Input Nodes
        for(int i = 0;i<numIn;i++){
            rect = new Rectangle((w+colSep)*curCol,yOrigin+((inStart+((h+rowSep)*(curRow+1)))*(int)Math.pow(inverter, (double)i)),w,h);
            // yOrigin + ((inStart + (curRow)*(rowWeight)) * (inv^i)
            // 90      + ((60      + (0     )*(60       )) * (1)
            // 90 + 60
            // 150
            GraphicObjects.add(rect);
//            curRow++;
        }
        curRow = 0;
        curCol++;

        // Paint Deep Nodes
        for(int i = 1;i<=numDeepCol;i++){
            for(int j=0;j<numDeepRow;j++){
//                rect = new Rectangle((w+colSep)*curCol,curRow*rowWeight,w,h);
//                GraphicObjects.add(rect);
                curRow++;
            }
            curRow = 0;
            curCol++;
        }
        curRow = 0;

        // Paint Output Nodes
        for(int i = 1; i <= numOut; i++) {
//            rect = new Rectangle((w+colSep)*curCol,curRow*rowWeight,w,h);
//            GraphicObjects.add(rect);
            curRow++;
        }
        curCol++;
        curRow = 0;
        System.out.println(GraphicObjects.size());
        drawingPane.revalidate();
        drawingPane.repaint();
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Neural Mapper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
//                exitActionPerformed(new java.awt.event.ActionEvent(this,0,"temp"));
            }
        });

        //Create and set up the content pane.
        newContentPane = new GUIControl();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public GUIControl getContentPane(){
        return (GUIControl)newContentPane;
    }
    
    public int print(Graphics gg, PageFormat pf, int page) throws PrinterException {
        if(page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g = (Graphics2D)gg;
        Rectangle rect;
            for (int i = 0; i < GraphicObjects.size(); i++) {
                rect = GraphicObjects.elementAt(i);
                g.setColor(colors.get(i));
//                Color clearColor = new Color(0,0,0,0);
                if(shapeList.get(i) == 0){
                    g.drawOval(rect.x, rect.y, rect.width, rect.height);
                }
                else if(shapeList.get(i) == 1){
                    g.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
                }
            }
        g.translate(pf.getImageableX(), pf.getImageableY());
        return PAGE_EXISTS;
    }
}



