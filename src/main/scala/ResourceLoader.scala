import io.Source

object ResourceLoader {

  /*
   * Returns a classpath resource identified by its absolute path.
   * @param resource absolute path to classpath resource.
   * @return String containing the loaded resource data.
   */
  def load(resource: String) = {
    val stream = ResourceLoader.getClass.getResourceAsStream(resource)
    require(stream != null, "Unable to load: " + resource)
    Source.fromInputStream(stream, "UTF-8").getLines().toList
  }
}
