package pondero.tests.staples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FrameSequence implements Iterable<Frame> {

    private final List<Frame> frames = new ArrayList<Frame>();

    public FrameSequence() {
    }

    public FrameSequence(final String expr) {
        final String[] foo = expr.split(";");
        for (final String bar : foo) {
            add(new Frame(bar));
        }
    }

    public void add(final Frame frame) {
        final int pos = Collections.binarySearch(frames, frame);
        if (pos >= 0) {
            final Frame foo = frames.get(pos);
            foo.addAll(frame.getContent());
        } else {
            frames.add(-pos - 1, frame);
        }
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Frame frame : frames) {
            sb.append(frame).append("; ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

}
