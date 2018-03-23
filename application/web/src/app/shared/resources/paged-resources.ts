import { EmbeddedResource } from "./embedded-resource";
import { PageMetaData } from "./page-meta-data";

export class PagedResources<T> {
  constructor(
    public resources: EmbeddedResource<T>[],
    public metaData: PageMetaData
  ) {
  }
}
