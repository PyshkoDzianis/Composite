package by.pyshkodzianis.task4.component;

import java.util.List;

public interface ComponentText {

    boolean addComponent(ComponentText text);

    boolean removeComponent(ComponentText text);


    ComponentType getType();

    List<ComponentText> getComponents();

    int countSymbol();

}