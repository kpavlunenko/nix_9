package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.db.impl.CompanyArrayDBImpl;
import ua.com.alevel.type.ApplicationType;
import ua.com.alevel.util.ResourcesUtil;

import java.util.Map;
import java.util.Set;

public class ApplicationConfig {

    public static <I> I getImpl(Class<I> iface) {
        Reflections scan = new Reflections("ua.com.alevel");
        Set<Class<? extends I>> impls = scan.getSubTypesOf(iface);
        for (Class<? extends I> iml : impls) {
            if (iface.isAssignableFrom(CompanyDB.class)) {
                Map<String, String> map = ResourcesUtil.getProperties(iface.getClassLoader());
                String db = map.get(ApplicationType.DB_TYPE.getType());
                if (db.equals(ApplicationType.DB_ARRAY_VALUE.getType())) {
                    return (I) CompanyArrayDBImpl.getInstance();
                }
            }
            if (!iml.isAnnotationPresent(Deprecated.class)) {
                try {
                    return iml.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
