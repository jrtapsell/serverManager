package uk.co.jrtapsell.serverManager.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.net.InetAddress;
import java.util.UUID;
import uk.co.jrtapsell.serverManager.database.Validatable;

public class Server implements Validatable {
  @Expose
  @SerializedName ("id")
  private final UUID id;

  @Expose
  @SerializedName ("name")
  private final String name;


  @Expose
  @SerializedName ("ip")
  private final String address;

  @Expose
  @SerializedName ("hostname")
  private final String hostname;

  public Server(UUID id, String name, String address, String hostname) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.hostname = hostname;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getHostname() {
    return hostname;
  }

  @Override
  public boolean validate() {
    return id != null && notNullOrEmpty(name, address, hostname);
  }
}