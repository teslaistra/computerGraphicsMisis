package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Lab14.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab14 implements GLEventListener {
    int w,h;
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab14());
        frame.add(canvas);
        frame.setSize(800, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
        animator.stop();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        w = width;
        h = height;
    } 
        
    void drawBox(GL gl, double outer_radius,double teeth_lenght, double height){
        gl.glBegin(GL.GL_LINE_LOOP);
        double r = outer_radius + teeth_lenght;
        
        gl.glVertex3d(r,r,0); 
        gl.glVertex3d(r,-r,0); 
        gl.glVertex3d(-r,-r,0);
        gl.glVertex3d(-r,r,0);
        

    }
    public static void gear(GL gl,
                          double inner_radius,
                          double outer_radius,
                          double teeth_lenght,
                         double height,
                          int teeth, 
                          int mode)
  {
    int i;
    double r0, r1, r2,inner_x1,inner_y1,inner_x2,inner_y2,outer_x1,outer_y1,outer_x2,outer_y2,inner_x3,inner_y3,inner_x4,inner_y4;
    double angle=0, da=2 * Math.PI / (teeth * 2);

    r0 = inner_radius;
    r1 = outer_radius - inner_radius;
    r2 = outer_radius + teeth_lenght;

    

    /* draw front face */
   
    for (i = 0; i <= teeth*2; i++)
      {      
        if(i % 2 == 0){
            //–исуем тонкую часть шестеренки
            
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = outer_radius*Math.cos(angle);
            outer_y1 = outer_radius*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = outer_radius*Math.cos(angle+da);
            outer_y2 = outer_radius*Math.sin(angle+da);
            gl.glColor3d(1,1,1);

            //верхн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,0);

                gl.glVertex3d(outer_x2, outer_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();

            //нижн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,height);

                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
            gl.glEnd();
            gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));

            //передний торец
            gl.glBegin(mode);
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
            
            //задний торец
            gl.glBegin(mode);
                gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glColor3d(Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)));

                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();
            
            
//            //бок 1
//            gl.glBegin(mode);
//            gl.glVertex3d(inner_x1, inner_y1,0);
//            gl.glVertex3d(inner_x1, inner_y1,height);
//            gl.glVertex3d(outer_x1, outer_y1,height);
//            gl.glVertex3d(outer_x1, outer_y1,0);
//            gl.glEnd();
//            
//            //бок 2
//            gl.glBegin(mode);
//            gl.glVertex3d(inner_x2, inner_y2,0);
//            gl.glVertex3d(inner_x2, inner_y2,height);
//            gl.glVertex3d(outer_x2, outer_y2,height);
//            gl.glVertex3d(outer_x2, outer_y2,0);
//            gl.glEnd();
        }
        else{ 
            // –исуем зубцы шестеренки
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = r2*Math.cos(angle);
            outer_y1 = r2*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = r2*Math.cos(angle+da);
            outer_y2 = r2*Math.sin(angle+da);
            
            inner_x3 = outer_radius*Math.cos(angle);
            inner_y3 = outer_radius*Math.sin(angle);
            
            inner_x4 = outer_radius*Math.cos(angle+da);
            inner_y4 = outer_radius*Math.sin(angle+da);  
            gl.glColor3d(1,1,1);

            //верхн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,0);

                gl.glVertex3d(outer_x2, outer_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();

            //нижн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,height);

                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
            gl.glEnd();
            
            //передний торец
            gl.glBegin(mode);
                gl.glColor3d(Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)), Math.abs(Math.cos(angle)));
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
            //задний торец
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glColor3d(Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)), Math.abs(Math.cos(angle+da)));

                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();
            
            
            //бок 1
            gl.glBegin(mode);
                gl.glVertex3d(inner_x3, inner_y3,0);
                gl.glVertex3d(inner_x3, inner_y3,height);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,0);
            gl.glEnd();
            
            //бок 2
            gl.glBegin(mode);
                gl.glVertex3d(inner_x2, inner_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
        }
 
        angle = angle + da;
        
        
      }
    
  }
    
    public static void SideGear(GL gl,
                          double inner_radius,
                          double outer_radius,
                          double teeth_lenght,
                         double height,
                          int teeth,int angle_rotation_down, int angle_rotation_up)
  {

    int i;
    double r0, r1, r2,inner_x1,inner_y1,inner_x2,inner_y2,outer_x1,outer_y1,outer_x2,outer_y2,inner_x3,inner_y3,inner_x4,inner_y4;
    double angle=0, da=2 * Math.PI / (teeth * 2);

    r0 = inner_radius;
    r1 = outer_radius - inner_radius;
    r2 = outer_radius + teeth_lenght;
    
    int mode = GL.GL_LINE_LOOP;
    gl.glBegin(GL.GL_LINE);
        gl.glVertex2d(outer_radius+teeth_lenght, 0);
        gl.glVertex2d(-(outer_radius+teeth_lenght), 0);

        gl.glVertex2d(outer_radius+teeth_lenght, height);
        gl.glVertex2d(-(outer_radius+teeth_lenght), height);
    gl.glEnd();

    for (i = 0; i <= teeth*2; i++)
      {      
        
        if(i % 2 == 0){
            
            checkLine(gl, angle_rotation_down, angle_rotation_up, angle);
            
//            //–исуем тонкую часть шестеренки
            gl.glBegin(mode);
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = outer_radius*Math.cos(angle);
            outer_y1 = outer_radius*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = outer_radius*Math.cos(angle+da);
            outer_y2 = outer_radius*Math.sin(angle+da);
            
            
          //там где оливковый красный 
          //зеленый на 90 повернуть 
            
            
            //передний торец
            gl.glBegin(mode);
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
//            
     
            

        }
        else{ 
            // –исуем зубцы шестеренки
            checkLine(gl, angle_rotation_down, angle_rotation_up, angle);
            // –исуем зубцы шестеренки
            gl.glBegin(mode);
            inner_x1 = inner_radius*Math.cos(angle);
            inner_y1 = inner_radius*Math.sin(angle);

            outer_x1 = r2*Math.cos(angle);
            outer_y1 = r2*Math.sin(angle);
            
            inner_x2 = inner_radius*Math.cos(angle+da);
            inner_y2 = inner_radius*Math.sin(angle+da);

            outer_x2 = r2*Math.cos(angle+da);
            outer_y2 = r2*Math.sin(angle+da);
            
            inner_x3 = outer_radius*Math.cos(angle);
            inner_y3 = outer_radius*Math.sin(angle);
            
            inner_x4 = outer_radius*Math.cos(angle+da);
            inner_y4 = outer_radius*Math.sin(angle+da);  
            
            //верхн€€ плоскость
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,0);

                gl.glVertex3d(outer_x2, outer_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();

            //нижн€€ плоскость
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,height);

                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
            gl.glEnd();
            
            //передний торец
            gl.glBegin(mode);
                gl.glVertex3d(outer_x1, outer_y1,0);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
            
            //задний торец
            gl.glBegin(mode);
                gl.glVertex3d(inner_x1, inner_y1,0);
                gl.glVertex3d(inner_x1, inner_y1,height);
                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(inner_x2, inner_y2,0);
            gl.glEnd();
            
            
            //бок 1
            gl.glBegin(mode);
                gl.glVertex3d(inner_x3, inner_y3,0);
                gl.glVertex3d(inner_x3, inner_y3,height);
                gl.glVertex3d(outer_x1, outer_y1,height);
                gl.glVertex3d(outer_x1, outer_y1,0);
            gl.glEnd();
            
            //бок 2
            gl.glBegin(mode);
                gl.glVertex3d(inner_x2, inner_y2,0);
                gl.glVertex3d(inner_x2, inner_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,height);
                gl.glVertex3d(outer_x2, outer_y2,0);
            gl.glEnd();
        }
        angle = angle + da;
      }
  }
    public static void checkLine(GL gl, int angle_rotation_down, int angle_rotation_up, double angle){
      
        if (angle_rotation_down < Math.toDegrees(angle) && Math.toDegrees(angle) < angle_rotation_up){
                gl.glEnable(GL.GL_LINE_STIPPLE);
                gl.glLineWidth(1);
                gl.glLineStipple(1, (short)0xFF00);
            }
            else {
                gl.glEnable(GL.GL_LINE_STIPPLE);
                gl.glLineWidth(3);
                gl.glLineStipple(1, (short)0xFFFF);
            }
    }
    
    public static void drawCentralLine(GL gl){
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glLineWidth(1);
        gl.glLineStipple(3, (short)0xFF12);
        gl.glBegin(GL.GL_LINE_LOOP);

            gl.glVertex2d(0, -0.8);                 
            gl.glVertex2d(0, 0.8);
        gl.glEnd();
         
        gl.glDisable(GL.GL_LINE_STIPPLE);
    }
    public static void upperGear(GL gl, double inner_radius,
                          double outer_radius,
                          double teeth_lenght,
                          int teeth){
        
        double angle=0, da=2 * Math.PI / (teeth * 2), r0,r1,r2;


                  
        r0 = inner_radius;
        r1 = outer_radius - inner_radius;
        r2 = outer_radius + teeth_lenght;
        for (int i = 0; i <= teeth*2; i++)
            {      
                gl.glBegin(GL.GL_LINES);

                if(i % 2 == 0){
                 
                    double x1 = outer_radius*Math.cos(angle);
                    double y1 = outer_radius*Math.sin(angle);
                
                    double x2 = outer_radius*Math.cos(angle+da);
                    double y2 = outer_radius*Math.sin(angle+da);

                        gl.glVertex2d(x1, y1);
                        gl.glVertex2d(x2, y2);
                    
                    double x5 = inner_radius*Math.cos(angle);
                    double y5 = inner_radius*Math.sin(angle);
                    double x6 = inner_radius*Math.cos(angle+da);
                    double y6 = inner_radius*Math.sin(angle+da);
                    
                        gl.glVertex2d(x5, y5);
                        gl.glVertex2d(x6, y6);
                  
                }
                
                else{
                    double x1 = (outer_radius + teeth_lenght)*Math.cos(angle);
                    double y1 = (outer_radius + teeth_lenght)*Math.sin(angle);
                

                    double x2 = (outer_radius + teeth_lenght)*Math.cos(angle+da);
                    double y2 = (outer_radius + teeth_lenght)*Math.sin(angle+da);
    
                
                    double x3 = (outer_radius)*Math.cos(angle+da);
                    double y3 = (outer_radius)*Math.sin(angle+da);
    
                    double x4 = (outer_radius)*Math.cos(angle);
                    double y4 = (outer_radius)*Math.sin(angle);
                        gl.glVertex2d(x4, y4);
                        gl.glVertex2d(x1, y1);
                        gl.glVertex2d(x1, y1);
                        gl.glVertex2d(x2, y2);
                        gl.glVertex2d(x2, y2);
                        gl.glVertex2d(x3, y3);
                    
                    double x5 = inner_radius*Math.cos(angle);
                    double y5 = inner_radius*Math.sin(angle);
                    double x6 = inner_radius*Math.cos(angle+da);
                    double y6 = inner_radius*Math.sin(angle+da);
                    
                        gl.glVertex2d(x5, y5);
                        gl.glVertex2d(x6, y6);

                }
                gl.glEnd();

                angle = angle + da;
            }

    }
      
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        // ѕервый вьюпорт
        gl.glViewport(0, h/2, w/2, h/2);
        gl.glScissor(0, h/2, w/2, h/2);
        gl.glEnable(GL.GL_SCISSOR_TEST);
        gl.glClearColor(0.5f, 0.5f, 0, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
       
        gl.glLoadIdentity();
        
        drawCentralLine(gl);

        gl.glRotatef(90, 1, 0, 0);
        gl.glRotatef(1, 0, 0, 1);

        SideGear(gl, 0.5, 0.6, 0.1, 0.2, 9, 0,180);

        // ¬торой вьюпорт
        gl.glViewport(w/2, h/2, w/2, h/2);
        gl.glScissor(w/2, h/2, w/2, h/2);
        gl.glLoadIdentity();
        gl.glClearColor(0, 0.5f, 0, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        drawCentralLine(gl);

        gl.glRotatef(92, 0, 1, 0);
        gl.glRotatef(90, 1, 0, 0); 

        SideGear(gl, 0.5, 0.6, 0.1, 0.2, 9,75,240);
        
        gl.glLoadIdentity();
        gl.glDisable(GL.GL_LINE_STIPPLE);

        // “ретий вьюпорт
        gl.glViewport(0, 0, w/2, h/2);
        gl.glScissor(0, 0, w/2, h/2);

        gl.glClearColor(0.5f, 0.0f, 0, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        drawCentralLine(gl);
        gl.glLineWidth(2);

        upperGear(gl, 0.5, 0.6, 0.1, 9);

        
        // „етвертый вьюпорт
        gl.glViewport(w/2, 0, w/2, h/2);
        gl.glScissor(w/2, 0, w/2, h/2);
        drawCentralLine(gl);
        
        gl.glRotatef(-120, 1, 0, 0);

        gear(gl, 0.5, 0.6, 0.1, 0.2, 19, GL.GL_POLYGON);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    
}



