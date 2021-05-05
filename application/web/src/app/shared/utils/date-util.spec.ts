import { DateUtil } from "./date-util";
import { InvalidDateFormatException } from "../exceptions/invalid-date-format-exception";

describe("DateUtil", () => {
    describe("isValidDate", () => {
        it("should return true when a date as string formatted in ISO standard is passed", () => {
            expect(DateUtil.isValidDate("2019-01-01")).toEqual(true);
        });

        it("should return false when a gibberish is passed", () => {
            expect(DateUtil.isValidDate("I am obviously not a date")).toEqual(false);
        });

        it("should return false when undefined is passed", () => {
            expect(DateUtil.isValidDate(undefined)).toEqual(false);
        });

        it("should return false when null is passed", () => {
            expect(DateUtil.isValidDate(null)).toEqual(false);
        });
    });

    describe("parse", () => {
        it(
            "should return a date with year, month, day, hour, minute and second values corresponding to the passed ISO formatted datestring",
            () => {
                const expectedDate: Date = new Date(2019, 0, 10, 9, 35, 20); // month is zero based

                expect(DateUtil.parse("2019-01-10T09:35:20")).toEqual(expectedDate);
            }
        );

        it(
            "should return a date with year, month and day values corresponding to the passed ISO formatted datestring at midnight",
            () => {
                const expectedDate: Date = new Date(2019, 0, 10, 0, 0, 0);

                expect(DateUtil.parse("2019-01-10")).toEqual(expectedDate);
            }
        );

        it("should return undefined when undefined is passed", () => {
            expect(DateUtil.parse(undefined)).toBeUndefined();
        });

        it("should return undefined when null is passed", () => {
            expect(DateUtil.parse(null)).toBeUndefined();
        });

        it("should throw an InvalidDateFormat exception when gibberish is passed", () => {
            expect(() => DateUtil.parse("I am obviously not a date")).toThrow(new InvalidDateFormatException());
        });
    });
});
