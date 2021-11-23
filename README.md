# Spring REST Dynamic Filter Example

This example shows how to implement a dynamic JSON transformation (filtering) in a Spring Boot REST controller. It is using AOP controller advice to change controller method output in runtime

## Details

### AOP Advice

```java
@ControllerAdvice
@SuppressWarnings("unused")
public class FilterAdvice implements ResponseBodyAdvice<Object> {

   @Override
   public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
      String fields = ((ServletServerHttpRequest) request).getServletRequest().getParameter("fields");
      return new FilterMappingJacksonValue<>(body, StringUtils.isEmpty(fields) ? new String[] {} : fields.split(","));
   }

   @Override
   public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
      return true;
   }
}
```
### FilterMappingJacksonValue

```java
public class FilterMappingJacksonValue<T> extends MappingJacksonValue {

   public FilterMappingJacksonValue(final T value, final String... filters) {
      super(value);
      setFilters(new SimpleFilterProvider().addFilter("dynamicFilter",
            filters.length > 0 ? SimpleBeanPropertyFilter.filterOutAllExcept(filters) : SimpleBeanPropertyFilter.serializeAll()));
   }
}
```

### Book

```java
@Data
@AllArgsConstructor
@JsonFilter("dynamicFilter")
public class Book {
   String name;
   String author;
}
```

### BookController

```java
@RestController
@SuppressWarnings("unused")
public class BookController {

   @GetMapping("/books")
   public List<Book> books() {
      List<Book> books = new ArrayList<>();
      books.add(new Book("Don Quixote", "Miguel de Cervantes"));
      books.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez"));
      return books;
   }
}
```
