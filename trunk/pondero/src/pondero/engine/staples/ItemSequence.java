package pondero.engine.staples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import pondero.util.StringUtil;

public class ItemSequence {

    private static final String RANDOM   = "random";
    // private static final String NOREPLACE = "noreplace";
    // private static final String REPLACE   = "replace";
    private static final String SEQUENCE = "sequence";

    private static final Random RAND     = new Random();

    private final List<String>  items    = new ArrayList<String>();

    public ItemSequence(final String expr) {
        final List<Frame> frames = new ArrayList<Frame>();
        final String[] subexprs = expr.split(";");
        for (final String subexpr : subexprs) {
            frames.addAll(parseSubexpr(subexpr));
        }
        Collections.sort(frames);
        for (final Frame frame : frames) {
            items.add(frame.getContent().iterator().next());
        }
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private List<Frame> parseSubexpr(final String expr) {
        final List<Frame> frames = new ArrayList<Frame>();
        final String[] foo = expr.split("=");

        final String[] frameGroups = foo[0].split(",");
        for (String group : frameGroups) {
            if (!StringUtil.isNullOrBlank(group)) {
                group = group.trim();
                if (group.contains("-")) {
                    final String[] bar = group.split("-");
                    final int from = Integer.parseInt(bar[0].trim());
                    final int to = Integer.parseInt(bar[1].trim());
                    for (int i = from; i <= to; ++i) {
                        frames.add(new Frame(i));
                    }
                } else {
                    frames.add(new Frame(Integer.parseInt(group)));
                }
            }
        }

        String selector = SEQUENCE;
        final List<String> itemList = new ArrayList<String>();
        String[] itemArray;
        if (foo[1].contains("(")) {
            final String[] bar = foo[1].split("\\(");
            selector = bar[0].trim().toLowerCase();
            itemArray = bar[1].substring(0, bar[1].lastIndexOf(")")).split(",");
        } else {
            itemArray = foo[1].split(",");
        }
        for (final String item : itemArray) {
            if (!StringUtil.isNullOrBlank(item)) {
                itemList.add(item.trim());
            }
        }

        if (SEQUENCE.equals(selector)) {
            for (int i = 0; i < frames.size(); ++i) {
                final Frame frame = frames.get(i);
                frame.add(itemList.get(i % itemList.size()));
            }
        } else if (RANDOM.equals(selector)) {
            for (int i = 0; i < frames.size(); ++i) {
                final Frame frame = frames.get(i);
                frame.add(itemList.get(RAND.nextInt(itemList.size())));
            }
        }

        return frames;
    }

}
