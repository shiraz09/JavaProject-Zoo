/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Graphics;
import java.awt.Graphics;

/** Interface definition IDrawable **/
public interface IDrawable {
    public final static String PICTURE_PATH = "לא לשכוח להוסיף נתב של התמונות…";
    public void loadImages(String nm);
    public void drawObject (Graphics g);
}