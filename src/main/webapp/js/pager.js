// 分页器脚本
(function () {
    class Pager {
        // 每页显示的个数
        pageSize;
        // 当前被选中的页
        pageNo;
        // 总共有多少页
        pageSum;
        // 总共有多少条数据
        pageTotal;
        // 默认显示的当前页的下标
        pageIndex;
        // 构造器
        constructor(pageTotal, pageSize = 5, pageNo = 1) {
            // 页码集合
            const pageList = document.querySelectorAll("#page-container .page-list .page-number");
            const pageListParent = document.querySelector("#page-container .page-list");
            if (pageTotal === undefined) {
                pageListParent.innerText = "Pager至少需要一个参数，请给定要分页的总数据";
                pageListParent.style.border = "5px solid red";
                pageListParent.style.color = "blue";
                throw new Error("Pager至少需要一个参数，请给定要分页的总数据");
            }
            if (pageNo < 1) {
                pageListParent.innerText = "当前显示页数不能小于1";
                pageListParent.style.border = "5px solid red";
                pageListParent.style.color = "blue";
                throw new Error("当前显示页数不能小于1");
            }
            if (pageNo >= 7) {
                this.pageIndex = 7;
            } else {
                this.pageIndex = pageNo - 1;
            }
            if (pageTotal < 1) {
                pageListParent.innerText = "总数据不能小于1";
                pageListParent.style.border = "5px solid red";
                pageListParent.style.color = "blue";
                throw new Error("总数据不能小于1");
            }
            if (pageSize < 1) {
                pageListParent.innerText = "每页至少显示1条数据";
                pageListParent.style.border = "5px solid red";
                pageListParent.style.color = "blue";
                throw new Error("每页至少显示1条数据");
            }
            this.pageSize = pageSize;
            this.pageNo = pageNo;
            this.pageTotal = pageTotal;
            // 根据总数据除以每页显示的数据得到总页数（向上取整）
            this.pageSum = Math.ceil(this.pageTotal / this.pageSize);

            if (pageNo > this.pageSum) {
                pageListParent.innerText = "当前显示页数不能大于总页数";
                pageListParent.style.border = "5px solid red";
                pageListParent.style.color = "blue";
                throw new Error("当前显示页数不能大于总页数");
            }
            // 数不够七页时删除一些页码
            if (this.pageSum <= 7) {
                for (let i = this.pageSum; i < pageList.length; i++) {
                    pageListParent.removeChild(pageList.item(i));
                }
            }
            // 初始化
            this.pageInit();
            this.pageMore(this.pageIndex);
        }

        // 初始化分页器
        pageInit() {
            // 当前页高亮显示css类
            let activ = "page-activation";
            // 表示更多css类，页面中用于显示三个点的页码 ...
            let more = "page-more";

            // 首页按钮
            const pageHome = document.querySelector("#page-container .page-home");
            // 尾页按钮
            const pageLast = document.querySelector("#page-container .page-last");
            // 上一页按钮
            const pageUpper = document.querySelector("#page-container .page-upper");
            // 下一页按钮
            const pageLower = document.querySelector("#page-container .page-lower");
            // 页数显示
            const sum = document.querySelector("#page-container .page-sum .sum");
            // 总数据
            const total = document.querySelector("#page-container .page-total .total");
            // 页码集合
            const pageList = document.querySelectorAll("#page-container .page-list .page-number");
            /*初始化一些数据（总页数，总数据，默认显示页数）*/
            // 显示 当前页数 / 总页数
            sum.innerText = this.pageNo + " / " + this.pageSum;
            total.innerText = this.pageTotal;
            // 高亮显示默认显示页数
            pageList.item(this.pageIndex).classList.add(activ);

            /*
                给 pageList 中的所有页码 设置标签属性的tabIndex
                element.tabIndex：设置或返回元素的标签顺序
                作用：用于更新当前显示的页的 index
            */
            pageList.forEach(function (item, index) {
                item.tabIndex = index;
            });
            /*
                给每一个页码绑定点击事件 
                1,获取所有页码的父节点（给父节点绑定事件，事件委派）
            */
            const _this = this;
            pageList.item(0).parentNode.onclick = function (event) {
                let e = event || window.event; // 获取事件对象 『兼容ie8及以下浏览器』
                // 返回触发事件对象 （e.srcElement非标准IE 6 - 8 使用）
                let target = e.target || e.srcElement;
                // 带有page-number类的元素节点点击时才触发事件
                if ([...target.classList].indexOf("page-number") != -1) {
                    // 带有page-more类的元素节点不触发事件
                    if ([...target.classList].indexOf("page-more") === -1) {
                        // 更新当前页
                        _this.pageNo = target.innerText;
                        _this.pageMore(target.tabIndex);
                    }
                }

            };
            /*
                首页，尾页，上一页，下一页绑定事件
            */
            pageHome.onclick = () => {
                this.pageTurning("home"); // 首页
            }
            pageLast.onclick = () => {
                this.pageTurning("last"); // 尾页
            }
            pageUpper.onclick = () => {
                this.pageTurning("upper"); // 上一页
            }
            pageLower.onclick = () => {
                this.pageTurning("lower"); // 下一页
            }
        }

        /*
            Page turning翻页
            参数：str，翻页的动作
                str === home：直接翻到首页
                str === last：直接翻到尾页
                str === upper：翻到上一页
                str === lower：翻到下一页
        */
        pageTurning(str) {
            // 当前页高亮显示css类
            let activ = "page-activation";
            // 表示更多css类，页面中用于显示三个点的页码 ...
            let more = "page-more";
            // 页码集合
            const pageList = document.querySelectorAll("#page-container .page-list .page-number");
            switch (str) {
                case "home":
                    // 更新当前页
                    this.pageNo = 1;
                    this.pageMore(0);
                    break;
                case "last":
                    // 更新当前页
                    this.pageNo = this.pageSum;
                    if (this.pageSum <= 7) {
                        this.pageMore(this.pageSum - 1);
                    } else {
                        this.pageMore(7);
                    }

                    break;
                case "upper":
                    if ((this.pageNo - 1) < 1) {
                        break;
                    }
                    if (this.pageIndex <= 1) {
                        this.pageIndex = 1;
                    }
                    // 更新当前页
                    this.pageNo -= 1;
                    this.pageMore(this.pageIndex - 1);
                    break;
                case "lower":
                    this.pageNo = parseInt(this.pageNo) + 1;
                    if (this.pageNo >= this.pageSum) {
                        this.pageNo = this.pageSum;
                    }
                    if (this.pageSum <= 7) {
                        if (this.pageIndex >= this.pageSum - 1) {
                            this.pageIndex = this.pageSum - 2;
                        }
                    } else {
                        if (this.pageIndex >= 7) {
                            this.pageIndex = 6;
                        }
                    }
                    this.pageMore(parseInt(this.pageIndex) + 1);
                    break;
            }
        }

        /*
            点击页为 7 页时，将更多类添加到下标为 2 的元素上
            显示规则 ：1 2 ... 5 6 7 8 9
                      1 2 ... 6 7 8 9 10
            参数：newIndex，更新之后的下标
        */
        pageMore(newIndex) {
            // 当前页高亮显示css类
            let activ = "page-activation";
            // 表示更多css类，页面中用于显示三个点的页码 ...
            let more = "page-more";
            // 页数显示
            const sum = document.querySelector("#page-container .page-sum .sum");
            // 页码集合
            const pageList = document.querySelectorAll("#page-container .page-list .page-number");

            // 更新当前页数
            sum.innerText = this.pageNo + " / " + this.pageSum;

            //移除原先高亮显示的页码
            pageList.item(this.pageIndex).classList.remove(activ);
            // 总页数少于 7 页时
            if (this.pageSum <= 7) {
                // 更新当前显示页的index
                this.pageIndex = newIndex;
                pageList.item(this.pageIndex).classList.add(activ);
                return;
            }
            // 页数大于或者等于7时
            if (this.pageNo >= 7) {
                // 更新要显示的页码下标
                this.pageIndex = newIndex;
                // 给下标为 2 添加更多显示css类 
                pageList.item(2).classList.add(more);
                // 从原先显示更多类的元素上移除css类
                pageList.item(7).classList.remove(more);

                // 下标为 2 显示 ...表示更多
                pageList.item(2).innerText = "...";
                // 循坏更新页码
                let count;
                if (this.pageNo > this.pageSum - 2) {
                    count = (this.pageSum - 2) - 2;
                    pageList.item(5).classList.remove(activ);
                    pageList.item(this.pageIndex).classList.add(activ);
                } else {
                    count = this.pageNo - 2;
                    // 重置高亮显示，高亮显示一直为下标第五的页码
                    pageList.item(5).classList.add(activ);
                }

                for (let i = 3; i < pageList.length; i++) {
                    pageList.item(i).innerText = count;
                    count++;
                }

            } else {
                if ([...pageList.item(2).classList].indexOf("page-more") != -1) {
                    pageList.item(5).classList.remove(activ);
                    pageList.item(newIndex).classList.remove(activ);
                    this.pageIndex = this.pageNo - 1;
                } else {
                    // 更新当前显示页的index
                    this.pageIndex = newIndex;
                }
                // 重置 更多类
                pageList.item(7).classList.add(more);
                pageList.item(2).classList.remove(more);
                pageList.item(7).innerText = "...";
                // 重置 页码
                let count = 3;
                for (let i = 2; i < pageList.length - 1; i++) {
                    pageList.item(i).innerText = count;
                    count++;
                }
                pageList.item(this.pageIndex).classList.add(activ);
            }
        }
    }
    try {
        new Pager(100);
    } catch (e) {
        // console.error(e.message);
    }

})();