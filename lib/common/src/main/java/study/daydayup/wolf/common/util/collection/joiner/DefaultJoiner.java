package study.daydayup.wolf.common.util.collection.joiner;

import lombok.Getter;
import study.daydayup.wolf.common.util.collection.joiner.exception.InvalidGetterException;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.common.util.collection.joiner
 *
 * @author Wingle
 * @since 2020/5/18 3:13 下午
 **/
public class DefaultJoiner<BASE, EXT> implements Joiner<BASE, EXT> {
    private static final int MIN_GETTER_LENGTH = 1;
    private static final int MAX_GETTER_LENGTH = 10;
    private static final String NULL_VALUE = "NULL_VALUE";
    private static final String KEY_DELIMITER = ":";

    private CollectionJoiner gateway;

    @Getter
    private Collection<BASE> base;
    @Getter
    private Function<BASE, Object>[] baseGetters;

    private Map<Object, EXT> extMap;

    public DefaultJoiner(Collection<BASE> base, CollectionJoiner gateway) {
        this.base = base;
        this.gateway = gateway;
    }

    @SafeVarargs
    @Override
    public final Joiner<BASE, EXT> on(BiConsumer<BASE, EXT> setter, Function<BASE, Object>... getters) {
        validBaseGetter(getters);
        return null;
    }

    @SafeVarargs
    @Override
    public final CollectionJoiner join(Collection<EXT> ext, Function<EXT, Object>... getters) {
        validExtGetter(getters);
        return gateway;
    }

    @SafeVarargs
    private final void validExtGetter(Function<EXT, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MAX_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }

    @SafeVarargs
    private final void validBaseGetter(Function<BASE, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MAX_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }

    private Object getBaseValue(BASE base, Function<BASE, Object>... getters) {
        Object value;
        if (1 == getters.length) {
            value = getters[0].apply(base);
            return formatValue(value);
        }

        StringBuilder sb = new StringBuilder();
        for (Function<BASE, Object> getter : getters) {
            value = getter.apply(base);
            sb.append(value).append(KEY_DELIMITER);
        }

        return sb.toString();
    }

    private Object getExtValue(EXT ext, Function<EXT, Object>... getters) {
        Object value;
        if (1 == getters.length) {
            value = getters[0].apply(ext);
            return formatValue(value);
        }

        StringBuilder sb = new StringBuilder();
        for (Function<EXT, Object> getter : getters) {
            value = getter.apply(ext);
            sb.append(value).append(KEY_DELIMITER);
        }

        return sb.toString();
    }

    private Object formatValue(Object value) {
        if (value != null) {
            return value;
        }

        return NULL_VALUE;
    }
}