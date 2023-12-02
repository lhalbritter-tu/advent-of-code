package AOC22.DayTenChallenge.Objects;

import java.util.ArrayList;
import java.util.List;

public class CRT {

    private int curRow, curCol, spritePos;

    public List<String[]> display;

    public CRT() {
        display = new ArrayList<>();
        curRow = 0;
        curCol = 0;
        spritePos = 1;
        this.display.add(new String[40]);
    }

    public void addRow() {
        this.display.add(new String[40]);
        curRow += 1;
        curCol = 0;
    }

    public void advance() {
        this.display.get(curRow)[curCol] = spriteInView() ? "#" : ".";
        System.out.println("CRT draws pixel in position " + curCol);
        curCol++;
        if (curCol >= 40) this.addRow();
    }

    public void moveSprite(long movement) {
        this.spritePos += movement;
    }

    public boolean spriteInView() {
        return spritePos == curCol || spritePos - 1 == curCol || spritePos + 1 == curCol;
    }

    public void display() {
        for (String[] row : display) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] == null ? "" : row[i]);
            }
            System.out.println();
        }
    }
}
