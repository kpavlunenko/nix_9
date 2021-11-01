package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.db.CustomerAgreementDB;
import ua.com.alevel.db.CustomerDB;
import ua.com.alevel.db.impl.CompanyArrayDBImpl;
import ua.com.alevel.db.impl.CustomerAgreementArrayDBImpl;
import ua.com.alevel.db.impl.CustomerArrayDBImpl;
import ua.com.alevel.type.ApplicationType;
import ua.com.alevel.util.ResourcesUtil;

import java.util.Map;
import java.util.Set;

public class ApplicationConfig {

    public static <I> I getImpl(Class<I> iface) {
        Reflections scan = new Reflections("ua.com.alevel");
        Set<Class<? extends I>> impls = scan.getSubTypesOf(iface);
        for (Class<? extends I> impl : impls) {
            if (iface.isAssignableFrom(CompanyDB.class)) {
                Map<String, String> map = ResourcesUtil.getProperties(iface.getClassLoader());
                String db = map.get(ApplicationType.DB_TYPE.getType());
                if (db.equals(ApplicationType.DB_ARRAY_VALUE.getType())) {
                    return (I) CompanyArrayDBImpl.getInstance();
                }
            }
            if (iface.isAssignableFrom(CustomerDB.class)) {
                Map<String, String> map = ResourcesUtil.getProperties(iface.getClassLoader());
                String db = map.get(ApplicationType.DB_TYPE.getType());
                if (db.equals(ApplicationType.DB_ARRAY_VALUE.getType())) {
                    return (I) CustomerArrayDBImpl.getInstance();
                }
            }
            if (iface.isAssignableFrom(CustomerAgreementDB.class)) {
                Map<String, String> map = ResourcesUtil.getProperties(iface.getClassLoader());
                String db = map.get(ApplicationType.DB_TYPE.getType());
                if (db.equals(ApplicationType.DB_ARRAY_VALUE.getType())) {
                    return (I) CustomerAgreementArrayDBImpl.getInstance();
                }
            }
            if (!impl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return impl.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
