import { Resource } from "../resources/original/resource";

export class ResourceUtil {
  static isSingleResource(resource: Resource): boolean {
    return resource["type"];
  }
}
