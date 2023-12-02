package AOC22.DayOneChallenge.Objects;

import java.util.Arrays;

public class ElfTree {
    private Elf elf;

    private ElfTree parent;
    private ElfTree left;
    private ElfTree right;

    private int size;

    public ElfTree() {
        size = 0;
    }

    public ElfTree(Elf elf) {
        this.elf = elf;
        size = 0;
    }

    public void setParent(ElfTree parent) {
        this.parent = parent;
    }

    public boolean add(Elf elf) {
        size += 1;
        if (this.elf == null) {
            this.elf = elf;
            return true;
        }

        if (this.elf.getTotal() < elf.getTotal()) {
            if (right == null) {
                right = new ElfTree(elf);
                right.setParent(this);
                return true;
            }
            else return right.add(elf);
        } else {
            if (left == null) {
                left = new ElfTree(elf);
                left.setParent(this);
                return true;
            }
            else return left.add(elf);
        }
    }

    public Elf remove(Elf elf) {
        if (this.elf.getTotal() < elf.getTotal()) {
            if (this.right != null) {
                if (this.right.elf == elf) {
                    ElfTree cur_right = this.right;
                    this.right = this.right.right;
                    if (this.right != null) {
                        this.right.left = cur_right.left;
                    }

                    return cur_right.elf;
                } else {
                    return this.right.remove(elf);
                }
            } else {
                return null;
            }
        } else {
            if (this.left != null) {
                if (this.left.elf == elf) {
                    ElfTree cur_left = this.left;
                    this.left = this.left.left;
                    if (this.left != null) {
                        this.left.right = cur_left.right;
                    }

                    return cur_left.elf;
                } else {
                    return this.left.remove(elf);
                }
            } else {
                return null;
            }
        }

    }

    public Elf max() {
        if (this.right == null) {
            return this.elf;
        }

        return this.right.max();
    }

    public ElfTree maxTree() {
        if (this.right == null) {
            return this;
        }

        return this.right.maxTree();
    }

    public Long top(int count) {
        Long result = 0L;
        String tree = toString();
        String[] splitted = tree.split(",");
        String[] subTree = Arrays.copyOfRange(splitted, splitted.length - count, splitted.length);

        for (String node : subTree) {
            result += Long.parseLong(node.strip());
        }

        return result;
    }

    @Override
    public String toString() {
        String left = this.left == null ? "" : this.left.getLeft() + ", ";
        String right = this.right == null ? "" : ", " + this.right.getRight();
        return left + elf.getTotal().toString() + right;
    }

    public String getLeft() {
        if (left == null) return elf.getTotal().toString();
        return this.left.toString() + ", " + elf.getTotal().toString();
    }

    public String getRight() {
        if (right == null) return getLeft();
        return getLeft() + ", " + this.right.toString();
    }

    public int size() {
        return size;
    }
}
