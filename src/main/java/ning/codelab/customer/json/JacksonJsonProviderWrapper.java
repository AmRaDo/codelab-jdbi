package ning.codelab.customer.json;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * RIP 2009-06-25. Returns a JacksonJsonProvider with a custom ObjectMapper to
 * allow mapping of custom types.
 */
@Singleton
public class JacksonJsonProviderWrapper implements Provider<JacksonJsonProvider>
{
    private final ObjectMapper objectMapper;

    @Inject
    public JacksonJsonProviderWrapper(final ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    public JacksonJsonProvider get()
    {
        return new JacksonJsonProvider(objectMapper);
    }
}
