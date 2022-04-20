package by.pyshkodzianis.task4.comparator;

import by.pyshkodzianis.task4.component.ComponentText;

import java.util.Comparator;

public class TextComparator implements Comparator<ComponentText> {
    @Override
    public int compare(ComponentText component1, ComponentText component2) {
        return Integer.compare(component1.getComponents().size(), component2.getComponents().size());
    }
}