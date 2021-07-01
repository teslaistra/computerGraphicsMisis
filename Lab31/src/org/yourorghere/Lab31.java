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
import java.nio.FloatBuffer;
import java.util.Random;


/**
 * Lab31.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab31 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab31());
        frame.add(canvas);
        frame.setSize(700, 700);
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

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glColor3d(1, 1, 1);
        
      
        
//        FloatBuffer fb;
//        fb = FloatBuffer.wrap(cp);
//        
        /*gl.glMap1f(//Вычислитель кривой
                задание цели(для нормалей, текстурных координат. GL_MAP1_Vertex_3(вычисление кривой по контрольным точкам из трех координат)),
 далее диапазон одномерной координаты: начало кривой(0), 1(конец кривой),
 далее параметры того как читать массив контрольных точек
        3(сколькими числами задается контрольная точка), 2(количество точек-порядок),
        массив контрольных точек); 

        */
//         
//        gl.glMap1f(GL.GL_MAP1_VERTEX_3, 0, 1, 3, 4, fb);
//        gl.glEnable(GL.GL_MAP1_VERTEX_3);//подключаем вычислитель
//        
//        // два режима организации работы вычислителя: 
//        //1. Псотроение отдельных точек
//        //2. Строим кривую целиком (проще)
//        
//        // идем по второму варианту
//        //делим кривую на части
//        //кривая строится дискретными участками и только на их концах используется вычислитель
//        gl.glMapGrid1f(20, 0, 1); // кривая бьется на 20 сегментов на участке от 0 до 1
//        gl.glEvalMesh1(GL.GL_LINE, 0, 20); //реализуем построение кривой. Режим построения, далее целочисленные значения сегментов которые будем строить
//        
//        //визуализируем набор точек
//        gl.glBegin(GL.GL_POINTS);
//        int i; 
//        for (i=1; i<=2; i++){
//            gl.glVertex3f(cp[i*3], cp[i*3+1], cp[i*3+2]);
//        }
//        gl.glEnd();
        
        float paint[] =
        {
            -0.9f, -0.6f, 0, -0.3f, -1f, 0,
            0.3f, -1f, 0, 0.6f, -0.6f, 0,
            
            -1f, -0.3f, 0, -0.3f, -0.3f, 0.5f,
            0.3f, -0.3f, 0.5f, 1f, -0.3f, 0,
            
            -1f, 0.3f, 0, -0.3f, 0.3f, 0.5f,
            0.3f, 0.3f, 0.5f, 1f, 0.3f, 0,
            
            -0.6f, 0.6f, 0, -0.3f, 1f, 0,
            0.3f, 1f, 0, 0.6f, 0.6f, 0,
        };
        
       
        
       float[] circle = {
           0.75f, 0.80f,0f,
           0.80f, 0.80f,0f,
           0.80f, 0.75f,0f,
           0.80f, 0.7f,0f,
            0.75f, 0.7f,0f,
            0.7f,  0.7f,0f,
            0.7f,  0.75f,0f,
            0.7f, 0.8f, 0f,
            0.75f, 0.80f,
            0f,0,0,0
       };
       
       float[] painter = {
           0, 0.4f, 0,
            0.86f, 0.7f, 0,
            0.78f, 0, 0,
            
            0.53f, -0.87f, 0,
            0f, -0.8f, 0,
            -0.95f, -0.75f, 0,
            
            -0.85f, 0, 0,
            -0.9f, 0.9f, 0,
            -0.5f,0.56f, 0,
            
            -0.5f,0.56f, 0,
            0, 0.4f, 0,
             0, 0.7f, 0
//            -0.85f, 0, 0,
//            -0.8f, 0.78f, 0,
//            0,0.56f, 0,   
           //  0,0,0
       };
       createSpline(gl, painter);
       
       gl.glPushMatrix();
       gl.glTranslated(-0.3, -0.5, 0);
       createSpline(gl, circle);
       gl.glPopMatrix();
       
       gl.glDisable(GL.GL_MAP1_VERTEX_3);
        
       double color_size = 0.1;
       gl.glPushMatrix();

       gl.glTranslated(-0.5, -0.3, 0);
       gl.glScaled(color_size, color_size, color_size);
       createSurface(gl, paint);
       
       gl.glTranslated(2, -2, 0);
       createSurface(gl, paint);

       gl.glTranslated(2.5, -1, 0);
       createSurface(gl, paint);

       gl.glTranslated(2.5, 1, 0);
       createSurface(gl, paint);
       
       gl.glTranslated(2.5, 1, 0);
       createSurface(gl, paint);
       
       gl.glTranslated(1.5, 2, 0);
       createSurface(gl, paint);
      
    }

    void createSpline(GL gl, float[] total_spline){
        for (int outer=0; outer < total_spline.length/6-1; outer++)
        {
            float[] part_spline = new float[9];
            for(int inner = 0; inner < 9; inner++){
                part_spline[inner]=total_spline[outer*6+inner];
            }
            FloatBuffer spline_Buf = FloatBuffer.wrap(part_spline);
            gl.glMap1f(GL.GL_MAP1_VERTEX_3, 0, 1, 3, 3, spline_Buf);
            gl.glEnable(GL.GL_MAP1_VERTEX_3);
            
            gl.glMapGrid1f(10, 0, 1);
            gl.glEvalMesh1(GL.GL_LINE, 0, 10);
        }
    }

    void createSurface(GL gl, float[] surface) {
        Random random = new Random();
        gl.glColor3d(random.nextDouble(),
                random.nextDouble(),
                random.nextDouble());
        
        FloatBuffer surface_Buf = FloatBuffer.wrap(surface);


        gl.glMap2f(GL.GL_MAP2_VERTEX_3, 0, 1, 3, 4, 0, 1, 12, 4, surface_Buf);
        //теперь два измерения
        // по первому измерению все изменяется от 0 до 1 
        // в пределах одного ряда каждая точка задается 3 числами и всего точек 4 
        // следующее измерение(ряды). 
        // все изменяется от 0 до 1 
        // каждый ряд задается 12 числами
        // порядок 4 так как 4 ряда 
        gl.glEnable(GL.GL_MAP2_VERTEX_3);

        gl.glMapGrid2f(5, 0, 1, 5, 0, 1);
        gl.glEvalMesh2(GL.GL_FILL, 0, 5, 0, 5);
        gl.glColor3d(0, 0, 0);
        gl.glEvalMesh2(GL.GL_LINE, 0, 5, 0, 5);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
