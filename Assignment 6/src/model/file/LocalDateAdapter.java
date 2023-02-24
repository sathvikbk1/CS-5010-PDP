package model.file;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * These helper functions are used to in deserializing and serializing the
 * LocalDate[Time] attribute because GSON fails to parse the value of the
 * attribute as it's not aware of the LocalDate[Time] objects.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
  @Override
  public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
    if (localDate != null) {
      jsonWriter.value(localDate.toString());
    } else {
      jsonWriter.value("null");
    }

  }

  @Override
  public LocalDate read(final JsonReader jsonReader) throws IOException {
    String str = jsonReader.nextString();
    if (str.equals("null")) {
      return null;
    }
    return LocalDate.parse(str);
  }
}