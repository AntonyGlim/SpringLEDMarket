package glim.antony.spring_led_market.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 * Артур Аракелов, [28.09.19 09:28]
 * Зачем нужен класс SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer?
 * Как понял из прочитанного вроде он нужен для работы фильтров.
 * Он сам из себя представляет фильтр сервлета и делегирует вызовы конкрентому классу унаследованному от Filter -
 * только вот проблема все и без него работают классы фильтров итак запускаются по крайне мере в спринг буте.
 * Может он необходим только в спринг mvc?
 *
 * доктор сказала пока, [28.09.19 09:40]
 * [In reply to Артур Аракелов]
 * избыточен, как и AppConfig, ибо @springbootapplication по сути и есть конфиг, бины можно прям там прописывать
 */
@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
