package com.sc.common.component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClazzComponentResolver的默认实现
 */
public class DefaultClazzComponentResolver extends AbstractComponentResolver<Class<?>>
                                           implements ClazzComponentResolver {

    public DefaultClazzComponentResolver(Component<Class<?>> component) {
        super(component);
    }


//
// 实现ClazzComponentResolver
//
    @Override
    public List<Class<?>> getAllSuperClass() {
        List<Class<?>> result = new ArrayList<>();
        List<Component<Class<?>>> childs = component.getChilds();
        getAllSuperClass(result, childs);
        return result;
    }

    private void getAllSuperClass(List<Class<?>> listResult, List<Component<Class<?>>> childs) {
        for(Component<Class<?>> child : childs) {
            if(!child.getInfo().isInterface()) {
                listResult.add(child.getInfo());
                getAllSuperClass(listResult, child.getChilds());
                break;
            }
        }
    }

    @Override
    public List<Class<?>> getAllInterfaces() {
        List<Class<?>> result = new ArrayList<>();
        List<Component<Class<?>>> childs = component.getChilds();
        getAllInterfaces(result, childs);
        return result;
    }

    private void getAllInterfaces(List<Class<?>> listResult, List<Component<Class<?>>> childs) {
        for(Component<Class<?>> child : childs) {
            if(child.getInfo().isInterface()) {
                listResult.add(child.getInfo());
                getAllInterfaces(listResult, child.getChilds());
            }
        }
    }


}
