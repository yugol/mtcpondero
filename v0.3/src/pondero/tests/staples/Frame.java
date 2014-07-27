package pondero.tests.staples;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Frame implements Comparable<Frame> {

    private int               index   = 0;
    private final Set<String> content = new HashSet<String>();

    public Frame() {
    }

    public Frame(final int index, final String... items) {
        this.index = index;
        for (final String item : items) {
            content.add(item);
        }
    }

    public Frame(final String expr) {
        final String[] frame = expr.split("=");
        index = Integer.parseInt(frame[0].trim());
        final String[] items = frame[1].split(",");
        for (final String item : items) {
            content.add(item.trim());
        }
    }

    public void add(final String item) {
        content.add(item);
    }

    public void addAll(final Collection<String> content) {
        this.content.addAll(content);
    }

    @Override
    public int compareTo(final Frame o) {
        if (index < o.index) { return -1; }
        if (index > o.index) { return 1; }
        return 0;
    }

    public Set<String> getContent() {
        return content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getIndex());
        sb.append(" =");
        for (final String item : content) {
            sb.append(" ").append(item).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}
