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
import jdk.nashorn.internal.ir.Flags;
import java.math.*;



/**
 * Lab12.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab13 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab13());
        frame.add(canvas);
        frame.setSize(640, 640);
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

   public void display(GLAutoDrawable drawable) {
       int cur_min = 10;
       int cur_hour = 14;
       int cur_second = 2;
       
        GL gl = drawable.getGL();
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        
        int minutes_total = 60; 
        double R = 0.7, alpha=0, min_angle, x,y;                 
        min_angle = 2 * Math.PI/minutes_total;
        
       // Рисуем циферблат 
        for(int i=1; i <= minutes_total; i++){
            if (i%5 == 1){
                gl.glPointSize(10);
                gl.glColor3f(1.0f,0.0f,0.0f);
            }
            else{
                gl.glPointSize(5);
                gl.glColor3f(1.0f,1.0f,1.0f);
            }
            gl.glBegin(GL.GL_POINTS);
                x = R*Math.cos(alpha);
                y = R*Math.sin(alpha);

                gl.glVertex2d(x, y);
            gl.glEnd();
            alpha = alpha+min_angle;
        }
        
        
        // Рисуем секундную стрелку; 
        gl.glLineStipple(2, (short)0xF00F);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINE_LOOP);
        
        gl.glColor3f(0.0f,1.0f,0.0f);
        
        x = -R*Math.cos((min_angle * cur_second) + Math.PI/2);
        y = R*Math.sin((min_angle * cur_second) + Math.PI/2);
        gl.glVertex2d(0.0f, 0.0f);
        gl.glVertex2d(x, y);
        gl.glEnd();
        
        
        // Рисуем минутную стрелку; 
        gl.glLineStipple(2, (short)0xDEFF);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINES);
        
        gl.glColor3f(0.0f,0.0f,1.0f);
        
        x = -R*Math.cos((min_angle * cur_min) +Math.PI/2);
        y = R*Math.sin((min_angle * cur_min) +Math.PI/2);
        gl.glVertex2d(0.0f, 0.0f);
        gl.glVertex2d(x, y);
        gl.glEnd();
        
        
        // Рисуем часовую стрелку; 
        gl.glLineStipple(1, (short)0xFFFF);
        gl.glEnable(GL.GL_LINE_STIPPLE);
        gl.glBegin(GL.GL_LINES);
        
        gl.glColor3f(1.0f,0.0f,1.0f);
        
        double cur_hour_ = (cur_hour - 12) * 5;
        
        
        //System.out.println((min_angle * cur_min ));
        //System.out.println((min_angle * cur_min ) + ((min_angle * 5/60) * cur_min));

        x = -(R/2)*Math.cos((min_angle * cur_hour_)+ (min_angle * 5/60) * cur_min +Math.PI/2);
        y = (R/2)*Math.sin((min_angle * cur_hour_)+ (min_angle * 5/60) * cur_min +Math.PI/2);
        gl.glVertex2d(0.0f, 0.0f);
        gl.glVertex2d(x, y);
        gl.glEnd();
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

