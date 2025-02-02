/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.reference.factories;

import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;
import io.annot8.implementations.reference.data.DefaultItem;
import io.annot8.implementations.support.registries.ContentBuilderFactoryRegistry;
import java.util.Objects;
import java.util.function.Consumer;

public class DefaultItemFactory implements ItemFactory {

  private final ContentBuilderFactoryRegistry contentBuilderFactoryRegistry;

  public DefaultItemFactory(ContentBuilderFactoryRegistry contentBuilderFactoryRegistry) {
    this.contentBuilderFactoryRegistry = contentBuilderFactoryRegistry;
  }

  @Override
  public Item create() {
    return new DefaultItem(this, contentBuilderFactoryRegistry);
  }

  @Override
  public Item create(Item parent) {
    Objects.requireNonNull(parent);
    return new DefaultItem(parent.getId(), this, contentBuilderFactoryRegistry);
  }

  @Override
  public Item create(Consumer<Item> func) {
    Item item = new DefaultItem(this, contentBuilderFactoryRegistry);
    func.accept(item);

    return item;
  }

  @Override
  public Item create(Item parent, Consumer<Item> func) {
    Objects.requireNonNull(parent);
    Item item = new DefaultItem(parent.getId(), this, contentBuilderFactoryRegistry);
    func.accept(item);

    return item;
  }
}
