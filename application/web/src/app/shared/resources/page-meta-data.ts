export class PageMetaData {
    constructor(
        public size: number,
        public totalElements: number,
        public totalPages: number,
        public number: number
    ) {
    }

    static toPageMetaData(toCopy: any) {
        return new PageMetaData(
            toCopy.size,
            toCopy.totalElements,
            toCopy.totalPages,
            toCopy.number
        );
    }
}
