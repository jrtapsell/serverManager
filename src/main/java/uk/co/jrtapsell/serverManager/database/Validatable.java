package uk.co.jrtapsell.serverManager.database;

import com.sun.media.sound.InvalidDataException;

/**
 * @author James Tapsell
 */
public interface Validatable {
  public boolean validate();


  default boolean notNullOrEmpty(String... fields) {
    for (String current : fields) {
      if (current == null || current.isEmpty()) {
        return false;
      }
    }
    return true;
  }

}
