package paper;

import java.io.Serializable;

/**
 * TriTyp
 *
 * @author Jackson Antonio do Prado Lima
 * @author Thiago Nascimento
 * @since 2016-04-27
 * @vesion 1.1
 */
public class TriTyp implements Serializable {

    private static final long serialVersionUID = 1L;
    public int i, j, k;
    public int trityp;
    public static int SCALENE = 1, ISOSCELES = 2, EQUILATERAL = 3, NOT_A_TRIANGLE = 4;

    public TriTyp() {

    }

    public void setI(int v) {
        i = v;
    }

    public void setJ(int v) {
        j = v;
    }

    public void setK(int v) {
        k = v;
    }
  
    public void type() {
        if (i == j) {
            trityp = trityp + 1;
        }
        if (i == k) {
            trityp = trityp + 2;
        }
        if (j == k) {
            trityp = trityp + 3;
        }

        if (i <= 0 || j <= 0 || k <= 0) {
            trityp = NOT_A_TRIANGLE;
            return;
        }
        if (trityp == 0) {
            if (i + j <= k || j + k <= i || i + k <= j) {
                trityp = NOT_A_TRIANGLE;
                return;
            } else {
                trityp = SCALENE;
                return;
            }
        }
        if (trityp > 3) {
            trityp = EQUILATERAL;
        } else {
            if (trityp == 1 && i + j > k) {
                trityp = ISOSCELES;
            } else {
                if (trityp == 2 && i + k > j) {
                    trityp = ISOSCELES;
                } else {
                    if (trityp == 3 && j + k > i) {
                        trityp = ISOSCELES;
                    } else {
                        trityp = NOT_A_TRIANGLE;
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TriTyp)) {
            return false;
        }
        TriTyp t = (TriTyp) o;
        return (i == t.i && j == t.j && k == t.k && trityp == t.trityp);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.i;
        hash = 61 * hash + this.j;
        hash = 61 * hash + this.k;
        hash = 61 * hash + this.trityp;
        return hash;
    }

    @Override
    public String toString() {
        String s = i + "-" + j + "-" + k + ": " + trityp;
        return s;
    }
}
