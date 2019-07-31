package de.d3adspace.phoenix.repository.yaml;

import de.d3adspace.phoenix.repository.map.AbstractMapCrudRepository;
import de.felixklauke.kira.core.Kira;
import de.felixklauke.kira.core.KiraFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

public class YamlCrudRepository<EntityType> extends
  AbstractMapCrudRepository<EntityType, String> {

  private static final Kira KIRA = KiraFactory.createKira();
  private static final DumperOptions DUMPER_OPTIONS = new DumperOptions();
  private static final Yaml DEFAULT_YAML = new Yaml(DUMPER_OPTIONS);

  static {
    DUMPER_OPTIONS.setPrettyFlow(true);
    DUMPER_OPTIONS.setDefaultFlowStyle(FlowStyle.BLOCK);
  }

  private final Yaml yaml;
  private final Path path;

  /**
   * Create a new yaml repository.
   *
   * @param entityClass The entities class.
   * @param yaml The yaml instance.
   * @param path The data file path.
   */
  protected YamlCrudRepository(Class<EntityType> entityClass, Yaml yaml, Path path) {
    super(entityClass, new HashMap<>());
    this.yaml = yaml;
    this.path = path;

    load();
  }

  /**
   * Create a new yaml repository.
   *
   * @param entityClass The entities class.
   * @param path The data file path.
   */
  public YamlCrudRepository(Class<EntityType> entityClass, Path path) {
    this(entityClass, DEFAULT_YAML, path);
  }

  public void load() {

    try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {

      Map<String, Map<String, Object>> map = yaml.loadAs(bufferedReader, Map.class);

      map.forEach((key, value) -> {

        EntityType deserialize = KIRA.deserialize(value, getEntityClass());
        getStorage().put(key, deserialize);
      });

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save() {

    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {

      Map<String, Map<String, Object>> documentContent = new HashMap<>();
      Map<String, EntityType> storage = getStorage();

      storage.forEach((key, value) -> {

        Map<String, Object> serialize = KIRA.serialize(value);
        documentContent.put(key, serialize);
      });

      String document = yaml.dumpAsMap(documentContent);
      bufferedWriter.write(document);
      bufferedWriter.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String nextId() {

    return UUID.randomUUID().toString();
  }
}
