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
 * Lab23.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab23 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab23());
        frame.add(canvas);
        frame.setSize(640, 480);
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
        
    }

    public void cylinder(GL gl, double alpha,double top,double bottom, double R,int n, double multiplier ){
        double x,y,da = 2 * Math.PI/n;

        gl.glBegin(GL.GL_QUAD_STRIP);

            while (alpha < Math.PI*2+da) {
                x = R*Math.cos(alpha);
                y = R*Math.sin(alpha);

                gl.glColor3d(0.8*Math.abs(Math.cos(alpha)), Math.abs(Math.cos(alpha))*0.7, Math.abs(Math.cos(alpha)*0.4));
                gl.glVertex3d(x, y, bottom);

                x = R*Math.cos(alpha);
                y = R*Math.sin(alpha);      

                gl.glVertex3d(multiplier*x,multiplier *y, top);
                alpha = alpha + da;
            }
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);
            alpha = 0;
            while (alpha < Math.PI*2+da) {
                x = R*multiplier*Math.cos(alpha);
                y = R*multiplier*Math.sin(alpha);
                alpha = alpha + da;
                gl.glColor3d(0.8*Math.abs(Math.cos(alpha)), Math.abs(Math.cos(alpha))*0.7, Math.abs(Math.cos(alpha)*0.4));

                gl.glVertex3d(x, y, top);
            }
        gl.glEnd();
        
    }
    
    public void generate_wool(GL gl, int n, double bottom, double top, double r, double multiplier){
        double alpha = 0, x, y, da = 2 * Math.PI/n;
        
        for(int i=0; i <= n; i++){
            gl.glBegin(GL.GL_LINES);
                gl.glColor3d(0.1*Math.random(), 0.1*Math.random(),0.1* Math.random());

                double R = Math.random() * r;
                x = R*Math.cos(alpha);
                y = R*Math.sin(alpha);

                gl.glVertex3d(x, y, bottom);
              
                gl.glColor3d(0.1*Math.random(), 0.1*Math.random(),0.1* Math.random());

                gl.glVertex3d(x*multiplier, y*multiplier,top);

            gl.glEnd();
            alpha = alpha+da;
        }
         
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glClearColor(0.5f, 0.5f, 0.5f, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glRotatef(45, 1, 0, 0);
        gl.glRotatef(45, 0, 0, 1);
        

        double R = 0.05;

        cylinder(gl, 0, -0.1, 1.3,R, 24, 1);

        cylinder(gl, 0,-0.7 , -0.1,R, 24, 0.5);
        
        generate_wool(gl, 40, -0.7, -1.0, R*0.5, 1.5);
//        gl.glBegin(GL.GL_TRIANGLE_FAN);
//            gl.glColor3f(0, 1, 0.0f);
//            gl.glVertex3d(0, 0, -1.4);
//            for(int i=0;i<=n; i++){
//                alpha = (float)(i*2*Math.PI/n);
//                x = (float) (R*Math.cos(alpha));
//                y = (float) (R*Math.sin(alpha));
//                gl.glColor3d(0, Math.abs(Math.cos(alpha)), 0);
//                gl.glVertex3d( x, y,-0.4);
//            }
//        gl.glEnd();
//        
//        

        
        //ove the "drawing cursor" around
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

