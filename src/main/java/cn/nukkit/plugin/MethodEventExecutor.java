package cn.nukkit.plugin;

import cn.nukkit.event.Event;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.EventException;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.*;

import java.lang.ref.WeakReference;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author MagicDroidX (Nukkit Project)
 */
@Slf4j
public class MethodEventExecutor implements EventExecutor {

    public static final AtomicInteger compileTime = new AtomicInteger(0);

    private final Method method;

    public MethodEventExecutor(Method method) {
        this.method = method;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(Listener listener, Event event) throws EventException {
        try {
            Class<Event>[] params = (Class<Event>[]) method.getParameterTypes();
            for (Class<Event> param : params) {
                if (param.isAssignableFrom(event.getClass())) {
                    method.invoke(listener, event);
                    break;
                }
            }
        } catch (InvocationTargetException ex) {
            throw new EventException(ex.getCause() != null ? ex.getCause() : ex);
        } catch (ClassCastException ex) {
            log.debug("Ignoring a ClassCastException", ex);
            // We are going to ignore ClassCastException because EntityDamageEvent can't be cast to EntityDamageByEntityEvent
        } catch (Throwable t) {
            throw new EventException(t);
        }
    }

    public Method getMethod() {
        return method;
    }

    public static EventExecutor compile(Class<? extends Listener> listenerClass, Method method) {
        return compile(listenerClass.getClassLoader(), listenerClass, method);
    }

    public static EventExecutor compile(ClassLoader classLoader, Class<? extends Listener> listenerClass, Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        var eventClass = method.getParameterTypes()[0];
        var eventType = Type.getType(eventClass);
        var listenerType = Type.getType(listenerClass);
        var internalName = "cn/nukkit/plugin/PNXMethodEventExecutor$" + compileTime.incrementAndGet();

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, internalName, null, "java/lang/Object", new String[]{"cn/nukkit/plugin/EventExecutor", "cn/nukkit/plugin/CompiledExecutor"});
        classWriter.visitSource("EventHandler@" + method.getDeclaringClass().getName() + "#" + method.getName(), null);
        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL, "originMethod", "Ljava/lang/reflect/Method;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/reflect/Method;)V", null, null);
            methodVisitor.visitCode();
            var label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(13, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            var label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(14, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, internalName, "originMethod", "Ljava/lang/reflect/Method;");
            var label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(15, label2);
            methodVisitor.visitInsn(RETURN);
            var label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "L" + internalName + ";", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("originMethod", "Ljava/lang/reflect/Method;", null, label0, label3, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "execute", "(Lcn/nukkit/event/Listener;Lcn/nukkit/event/Event;)V", null, new String[]{"cn/nukkit/utils/EventException"});
            methodVisitor.visitCode();
            var label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitTypeInsn(INSTANCEOF, eventType.getInternalName());
            var label1 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label1);
            var label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitTypeInsn(CHECKCAST, listenerType.getInternalName());
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitTypeInsn(CHECKCAST, eventType.getInternalName());
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, listenerType.getInternalName(), method.getName(), "(" + eventType.getDescriptor() + ")V", false);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            var label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "L" + internalName + ";", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("listener", "Lcn/nukkit/event/Listener;", null, label0, label3, 1);
            methodVisitor.visitLocalVariable("event", "Lcn/nukkit/event/Event;", null, label0, label3, 2);
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getOriginMethod", "()Ljava/lang/reflect/Method;", null, null);
            methodVisitor.visitCode();
            var label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, internalName, "originMethod", "Ljava/lang/reflect/Method;");
            methodVisitor.visitInsn(ARETURN);
            var label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "L" + internalName + ";", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        try {
            var clazz = loadClass(classLoader, classWriter.toByteArray());
            return (EventExecutor) clazz.getConstructor(Method.class).newInstance(method);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    private static WeakReference<Method> defineClassMethodRef = new WeakReference<>(null);

    private static Class<?> loadClass(ClassLoader loader, byte[] b) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InaccessibleObjectException {
        Class<?> clazz;
        java.lang.reflect.Method method;
        if (defineClassMethodRef.get() == null) {
            var cls = Class.forName("java.lang.ClassLoader");
            method = cls.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            defineClassMethodRef = new WeakReference<>(method);
        } else {
            method = defineClassMethodRef.get();
        }
        Objects.requireNonNull(method).setAccessible(true);
        try {
            var args =
                    new Object[]{"cn.nukkit.plugin.PNXMethodEventExecutor$" + compileTime.get(), b, 0, b.length};
            clazz = (Class<?>) method.invoke(loader, args);
        } finally {
            method.setAccessible(false);
        }
        return clazz;
    }
}
