package com.zy.mytest.bean;

import java.util.List;

public class Sku {
    /**
     * sku : {"skuBase":{"skus":[{"propPath":"1500:1540;1510:2027;2023:2074;2025:2075;2028:2029;2031:2032;2033:2034;2035:2056;2037:2044","skuId":4535},{"propPath":"1500:2059;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2076;2037:2038","skuId":4536},{"propPath":"1500:2030;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2036;2037:2038","skuId":4537}],"props":[{"children":[{"id":1540,"name":"10","pid":1500},{"id":2030,"name":"234","pid":1500},{"id":2059,"name":"12","pid":1500}],"id":1500,"name":"尺寸"},{"children":[{"id":2027,"name":"戒指","pid":1510}],"id":1510,"name":"品类"},{"children":[{"id":2024,"name":"18K红钻石（白）戒指","pid":2023},{"id":2074,"name":"0813现货1","pid":2023}],"id":2023,"name":"商品名称"},{"children":[{"id":2026,"name":"18K红","pid":2025},{"id":2075,"name":"驹","pid":2025}],"id":2025,"name":"成色"},{"children":[{"id":2029,"name":"钻石（白）","pid":2028}],"id":2028,"name":"石类"},{"children":[{"id":2032,"name":"0","pid":2031}],"id":2031,"name":"主石重(ct)"},{"children":[{"id":2034,"name":"0","pid":2033}],"id":2033,"name":"副石重(ct)"},{"children":[{"id":2036,"name":"234000","pid":2035},{"id":2056,"name":"0","pid":2035},{"id":2076,"name":"56","pid":2035}],"id":2035,"name":"净金重(g)"},{"children":[{"id":2038,"name":"234000","pid":2037},{"id":2044,"name":"10000","pid":2037}],"id":2037,"name":"总重量"}]}}
     */

    public SkuBean sku;

    public static class SkuBean {
        /**
         * skuBase : {"skus":[{"propPath":"1500:1540;1510:2027;2023:2074;2025:2075;2028:2029;2031:2032;2033:2034;2035:2056;2037:2044","skuId":4535},{"propPath":"1500:2059;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2076;2037:2038","skuId":4536},{"propPath":"1500:2030;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2036;2037:2038","skuId":4537}],"props":[{"children":[{"id":1540,"name":"10","pid":1500},{"id":2030,"name":"234","pid":1500},{"id":2059,"name":"12","pid":1500}],"id":1500,"name":"尺寸"},{"children":[{"id":2027,"name":"戒指","pid":1510}],"id":1510,"name":"品类"},{"children":[{"id":2024,"name":"18K红钻石（白）戒指","pid":2023},{"id":2074,"name":"0813现货1","pid":2023}],"id":2023,"name":"商品名称"},{"children":[{"id":2026,"name":"18K红","pid":2025},{"id":2075,"name":"驹","pid":2025}],"id":2025,"name":"成色"},{"children":[{"id":2029,"name":"钻石（白）","pid":2028}],"id":2028,"name":"石类"},{"children":[{"id":2032,"name":"0","pid":2031}],"id":2031,"name":"主石重(ct)"},{"children":[{"id":2034,"name":"0","pid":2033}],"id":2033,"name":"副石重(ct)"},{"children":[{"id":2036,"name":"234000","pid":2035},{"id":2056,"name":"0","pid":2035},{"id":2076,"name":"56","pid":2035}],"id":2035,"name":"净金重(g)"},{"children":[{"id":2038,"name":"234000","pid":2037},{"id":2044,"name":"10000","pid":2037}],"id":2037,"name":"总重量"}]}
         */

        public SkuBaseBean skuBase;

        public static class SkuBaseBean {
            public List<SkusBean> skus;
            public List<PropsBean> props;

            public static class SkusBean {
                /**
                 * propPath : 1500:1540;1510:2027;2023:2074;2025:2075;2028:2029;2031:2032;2033:2034;2035:2056;2037:2044
                 * skuId : 4535
                 */

                public String propPath;
                public int skuId;
            }

            public static class PropsBean {
                /**
                 * children : [{"id":1540,"name":"10","pid":1500},{"id":2030,"name":"234","pid":1500},{"id":2059,"name":"12","pid":1500}]
                 * id : 1500
                 * name : 尺寸
                 */

                public int id;
                public String name;
                public List<ChildrenBean> children;

                public static class ChildrenBean {
                    /**
                     * id : 1540
                     * name : 10
                     * pid : 1500
                     */

                    public int id;
                    public String name;
                    public int pid;
                    public boolean isSelect;
                    public boolean isNo;
                }
            }
        }
    }
}
