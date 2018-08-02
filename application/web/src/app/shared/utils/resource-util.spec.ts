import { Link } from "../resources/link";
import { SingleResource } from "../resources/original/single-resource";
import { ResourceUtil } from "./resource-util";
import { MultipleResources } from "../resources/original/multiple-resources";
import { PageMetaData } from "../resources/page-meta-data";

describe('ResourceUtil', () => {
  describe("isSingleResource", () => {
    it("should return true if the passed resource has attribute type", () => {
      const resource: SingleResource = {
        links: [new Link("self", "http://localhost")],
        type: "type"
      };

      expect(ResourceUtil.isSingleResource(resource)).toBeTruthy();
    });

    it("should return false if the passed resource does not have the attribute type", () => {
      const resource: MultipleResources = {
        links: [new Link("self", "http://localhost")],
        content: [],
        page: new PageMetaData(10, 10, 10, 10)
      };

      expect(ResourceUtil.isSingleResource(resource)).toBeFalsy();
    });
  });
});
