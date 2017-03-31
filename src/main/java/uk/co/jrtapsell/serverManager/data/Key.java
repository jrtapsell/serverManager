package uk.co.jrtapsell.serverManager.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import uk.co.jrtapsell.serverManager.database.Validatable;

public class Key implements Validatable{
  @Expose
  @SerializedName ("id")
  private final UUID id;

  @Expose
  @SerializedName("name")
  private final String name;

  @Expose(serialize = false, deserialize = true)
  @SerializedName("data")
  private final String data;

  public Key(UUID id, String name, String data) {
    this.id = id;
    this.name = name;
    this.data = data;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getData() {
    return data;
  }

  @Override
  public boolean validate() {
    return id != null && notNullOrEmpty(name, data);
  }
}
