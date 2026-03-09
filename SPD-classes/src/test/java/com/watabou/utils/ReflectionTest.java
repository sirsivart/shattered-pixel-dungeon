package com.watabou.utils;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

public class ReflectionTest {

    interface InterfaceA {}
    interface InterfaceB {}
    interface InterfaceC extends InterfaceA {}

    class DummyClass implements InterfaceA, InterfaceB {}
    class SubDummyClass extends DummyClass implements InterfaceC {}

    @Test
    public void testGetInterfaces() {
        Set<Class<?>> interfaces = Reflection.getInterfaces(DummyClass.class);
        assertEquals(2, interfaces.size());
        assertTrue(interfaces.contains(InterfaceA.class));
        assertTrue(interfaces.contains(InterfaceB.class));
    }

    @Test
    public void testGetInterfacesWithInheritance() {
        Set<Class<?>> interfaces = Reflection.getInterfaces(SubDummyClass.class);
        assertEquals(3, interfaces.size());
        assertTrue(interfaces.contains(InterfaceA.class));
        assertTrue(interfaces.contains(InterfaceB.class));
        assertTrue(interfaces.contains(InterfaceC.class));
    }

    @Test
    public void testGetInterfacesNoInterfaces() {
        Set<Class<?>> interfaces = Reflection.getInterfaces(Object.class);
        assertTrue(interfaces.isEmpty());
    }
}
