// modules are defined as an array
// [ module function, map of requires ]
//
// map of requires is short require name -> numeric require
//
// anything defined in a previous bundle is accessed via the
// orig method which is the require for previous bundles
parcelRequire = (function (modules, cache, entry, globalName) {
  // Save the require from previous bundle to this closure if any
  var previousRequire = typeof parcelRequire === 'function' && parcelRequire;
  var nodeRequire = typeof require === 'function' && require;

  function newRequire(name, jumped) {
    if (!cache[name]) {
      if (!modules[name]) {
        // if we cannot find the module within our internal map or
        // cache jump to the current global require ie. the last bundle
        // that was added to the page.
        var currentRequire = typeof parcelRequire === 'function' && parcelRequire;
        if (!jumped && currentRequire) {
          return currentRequire(name, true);
        }

        // If there are other bundles on this page the require from the
        // previous one is saved to 'previousRequire'. Repeat this as
        // many times as there are bundles until the module is found or
        // we exhaust the require chain.
        if (previousRequire) {
          return previousRequire(name, true);
        }

        // Try the node require function if it exists.
        if (nodeRequire && typeof name === 'string') {
          return nodeRequire(name);
        }

        var err = new Error('Cannot find module \'' + name + '\'');
        err.code = 'MODULE_NOT_FOUND';
        throw err;
      }

      localRequire.resolve = resolve;
      localRequire.cache = {};

      var module = cache[name] = new newRequire.Module(name);

      modules[name][0].call(module.exports, localRequire, module, module.exports, this);
    }

    return cache[name].exports;

    function localRequire(x){
      return newRequire(localRequire.resolve(x));
    }

    function resolve(x){
      return modules[name][1][x] || x;
    }
  }

  function Module(moduleName) {
    this.id = moduleName;
    this.bundle = newRequire;
    this.exports = {};
  }

  newRequire.isParcelRequire = true;
  newRequire.Module = Module;
  newRequire.modules = modules;
  newRequire.cache = cache;
  newRequire.parent = previousRequire;
  newRequire.register = function (id, exports) {
    modules[id] = [function (require, module) {
      module.exports = exports;
    }, {}];
  };

  var error;
  for (var i = 0; i < entry.length; i++) {
    try {
      newRequire(entry[i]);
    } catch (e) {
      // Save first error but execute all entries
      if (!error) {
        error = e;
      }
    }
  }

  if (entry.length) {
    // Expose entry point to Node, AMD or browser globals
    // Based on https://github.com/ForbesLindesay/umd/blob/master/template.js
    var mainExports = newRequire(entry[entry.length - 1]);

    // CommonJS
    if (typeof exports === "object" && typeof module !== "undefined") {
      module.exports = mainExports;

    // RequireJS
    } else if (typeof define === "function" && define.amd) {
     define(function () {
       return mainExports;
     });

    // <script>
    } else if (globalName) {
      this[globalName] = mainExports;
    }
  }

  // Override the current require with this new one
  parcelRequire = newRequire;

  if (error) {
    // throw error from earlier, _after updating parcelRequire_
    throw error;
  }

  return newRequire;
})({"FOel":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var Router =
/** @class */
function () {
  function Router(url) {
    this.routeUrl = url;
    this.routeTable = [];
  }

  Router.prototype.addRoutePath = function (path, page) {
    this.routeTable.push({
      path: path,
      page: page
    });
  };

  Router.prototype.route = function () {
    for (var _i = 0, _a = this.routeTable; _i < _a.length; _i++) {
      var RouteInfo_1 = _a[_i];

      if (this.routeUrl.indexOf(RouteInfo_1.path) >= 0) {
        RouteInfo_1.page.render();
        break;
      }
    }
  };

  return Router;
}();

exports.default = Router;
},{}],"yLDq":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var View =
/** @class */
function () {
  function View() {
    var logoElement = document.getElementById('logo');

    if (!logoElement) {
      throw 'logo가 없어 UI를 진행하지 못합니다.';
    }

    this.logo = logoElement;
    this.logo.addEventListener('click', function () {
      return location.href = '/home';
    });
    this.htmlList = [];
  }

  View.prototype.addHtml = function (htmlString) {
    this.htmlList.push(htmlString);
  };

  View.prototype.getHtml = function () {
    var snapshot = this.htmlList.join('');
    this.clearHtmlList();
    return snapshot;
  };

  View.prototype.clearHtmlList = function () {
    this.htmlList = [];
  };

  return View;
}();

exports.default = View;
},{}],"R1dU":[function(require,module,exports) {
"use strict";

var __extends = this && this.__extends || function () {
  var _extendStatics = function extendStatics(d, b) {
    _extendStatics = Object.setPrototypeOf || {
      __proto__: []
    } instanceof Array && function (d, b) {
      d.__proto__ = b;
    } || function (d, b) {
      for (var p in b) {
        if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
      }
    };

    return _extendStatics(d, b);
  };

  return function (d, b) {
    if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");

    _extendStatics(d, b);

    function __() {
      this.constructor = d;
    }

    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
  };
}();

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});

var view_1 = __importDefault(require("../core/view"));

var HomeView =
/** @class */
function (_super) {
  __extends(HomeView, _super);

  function HomeView() {
    return _super.call(this) || this;
  }

  HomeView.prototype.addEvent = function () {};

  HomeView.prototype.render = function () {};

  return HomeView;
}(view_1.default);

exports.default = HomeView;
},{"../core/view":"yLDq"}],"zCWJ":[function(require,module,exports) {
"use strict";

var __extends = this && this.__extends || function () {
  var _extendStatics = function extendStatics(d, b) {
    _extendStatics = Object.setPrototypeOf || {
      __proto__: []
    } instanceof Array && function (d, b) {
      d.__proto__ = b;
    } || function (d, b) {
      for (var p in b) {
        if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
      }
    };

    return _extendStatics(d, b);
  };

  return function (d, b) {
    if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");

    _extendStatics(d, b);

    function __() {
      this.constructor = d;
    }

    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
  };
}();

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});

var view_1 = __importDefault(require("../core/view"));

var ProductDetailView =
/** @class */
function (_super) {
  __extends(ProductDetailView, _super);

  function ProductDetailView(containerId) {
    var _this = _super.call(this) || this;

    var containerElement = document.getElementById(containerId);
    console.log(containerElement);

    if (!containerElement) {
      throw 'image 컨테이너가 없습니다.';
    }

    _this.imageContainer = containerElement;
    return _this;
  }

  ProductDetailView.prototype.addEvent = function () {
    var _this = this;

    var images = document.querySelectorAll("div.small-image-view > img");
    images.forEach(function (image) {
      return image.addEventListener("mouseenter", function (event) {
        if (_this.imageContainer) {
          _this.imageContainer.src = image.src;
        }
      });
    });
  };

  ProductDetailView.prototype.render = function () {
    console.log(this.imageContainer);
    this.addEvent();
  };

  return ProductDetailView;
}(view_1.default);

exports.default = ProductDetailView;
},{"../core/view":"yLDq"}],"il3j":[function(require,module,exports) {
"use strict";

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.ProductDetailView = exports.HomeView = void 0;

var home_view_1 = require("./home-view");

Object.defineProperty(exports, "HomeView", {
  enumerable: true,
  get: function get() {
    return __importDefault(home_view_1).default;
  }
});

var product_detail_view_1 = require("./product-detail-view");

Object.defineProperty(exports, "ProductDetailView", {
  enumerable: true,
  get: function get() {
    return __importDefault(product_detail_view_1).default;
  }
});
},{"./home-view":"R1dU","./product-detail-view":"zCWJ"}],"WIJd":[function(require,module,exports) {
"use strict";

var __extends = this && this.__extends || function () {
  var _extendStatics = function extendStatics(d, b) {
    _extendStatics = Object.setPrototypeOf || {
      __proto__: []
    } instanceof Array && function (d, b) {
      d.__proto__ = b;
    } || function (d, b) {
      for (var p in b) {
        if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
      }
    };

    return _extendStatics(d, b);
  };

  return function (d, b) {
    if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");

    _extendStatics(d, b);

    function __() {
      this.constructor = d;
    }

    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
  };
}();

var __assign = this && this.__assign || function () {
  __assign = Object.assign || function (t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
      s = arguments[i];

      for (var p in s) {
        if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
      }
    }

    return t;
  };

  return __assign.apply(this, arguments);
};

var __awaiter = this && this.__awaiter || function (thisArg, _arguments, P, generator) {
  function adopt(value) {
    return value instanceof P ? value : new P(function (resolve) {
      resolve(value);
    });
  }

  return new (P || (P = Promise))(function (resolve, reject) {
    function fulfilled(value) {
      try {
        step(generator.next(value));
      } catch (e) {
        reject(e);
      }
    }

    function rejected(value) {
      try {
        step(generator["throw"](value));
      } catch (e) {
        reject(e);
      }
    }

    function step(result) {
      result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected);
    }

    step((generator = generator.apply(thisArg, _arguments || [])).next());
  });
};

var __generator = this && this.__generator || function (thisArg, body) {
  var _ = {
    label: 0,
    sent: function sent() {
      if (t[0] & 1) throw t[1];
      return t[1];
    },
    trys: [],
    ops: []
  },
      f,
      y,
      t,
      g;
  return g = {
    next: verb(0),
    "throw": verb(1),
    "return": verb(2)
  }, typeof Symbol === "function" && (g[Symbol.iterator] = function () {
    return this;
  }), g;

  function verb(n) {
    return function (v) {
      return step([n, v]);
    };
  }

  function step(op) {
    if (f) throw new TypeError("Generator is already executing.");

    while (_) {
      try {
        if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
        if (y = 0, t) op = [op[0] & 2, t.value];

        switch (op[0]) {
          case 0:
          case 1:
            t = op;
            break;

          case 4:
            _.label++;
            return {
              value: op[1],
              done: false
            };

          case 5:
            _.label++;
            y = op[1];
            op = [0];
            continue;

          case 7:
            op = _.ops.pop();

            _.trys.pop();

            continue;

          default:
            if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
              _ = 0;
              continue;
            }

            if (op[0] === 3 && (!t || op[1] > t[0] && op[1] < t[3])) {
              _.label = op[1];
              break;
            }

            if (op[0] === 6 && _.label < t[1]) {
              _.label = t[1];
              t = op;
              break;
            }

            if (t && _.label < t[2]) {
              _.label = t[2];

              _.ops.push(op);

              break;
            }

            if (t[2]) _.ops.pop();

            _.trys.pop();

            continue;
        }

        op = body.call(thisArg, _);
      } catch (e) {
        op = [6, e];
        y = 0;
      } finally {
        f = t = 0;
      }
    }

    if (op[0] & 5) throw op[1];
    return {
      value: op[0] ? op[1] : void 0,
      done: true
    };
  }
};

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.SellListApi = void 0;

var Api =
/** @class */
function () {
  function Api(url) {
    this.url = url;
  }

  Api.prototype.getRequest = function () {
    return __awaiter(this, void 0, Promise, function () {
      var response;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            return [4
            /*yield*/
            , fetch(this.url, {
              method: 'GET',
              headers: {
                "Content-Type": "application/json"
              }
            })];

          case 1:
            response = _a.sent();
            return [4
            /*yield*/
            , response.json()];

          case 2:
            return [2
            /*return*/
            , _a.sent()];
        }
      });
    });
  };

  Api.prototype.postRequest = function (method, body) {
    return __awaiter(this, void 0, Promise, function () {
      var response;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            return [4
            /*yield*/
            , fetch(this.url, {
              method: method,
              headers: {
                "Content-Type": "application/json"
              },
              body: JSON.stringify(__assign({}, body))
            })];

          case 1:
            response = _a.sent();
            return [4
            /*yield*/
            , response.json()];

          case 2:
            return [2
            /*return*/
            , _a.sent()];
        }
      });
    });
  };

  return Api;
}();

var MypageApi =
/** @class */
function (_super) {
  __extends(MypageApi, _super);

  function MypageApi(url) {
    return _super.call(this, url) || this;
  }

  MypageApi.prototype.getData = function () {
    return __awaiter(this, void 0, Promise, function () {
      return __generator(this, function (_a) {
        return [2
        /*return*/
        , this.getRequest()];
      });
    });
  };

  return MypageApi;
}(Api);

var SellListApi =
/** @class */
function (_super) {
  __extends(SellListApi, _super);

  function SellListApi(url) {
    return _super.call(this, url) || this;
  }

  SellListApi.prototype.getData = function () {
    return __awaiter(this, void 0, Promise, function () {
      return __generator(this, function (_a) {
        return [2
        /*return*/
        , this.getRequest()];
      });
    });
  };

  return SellListApi;
}(Api);

exports.SellListApi = SellListApi;
},{}],"M5zi":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.PRODUCT_LIST_URL = void 0;
exports.PRODUCT_LIST_URL = 'http://localhost:8080/api/product/@type/list/page/@page/amount/@amount?category=@category&location=@location&sort=@sort';
},{}],"LhbK":[function(require,module,exports) {
"use strict";

var __extends = this && this.__extends || function () {
  var _extendStatics = function extendStatics(d, b) {
    _extendStatics = Object.setPrototypeOf || {
      __proto__: []
    } instanceof Array && function (d, b) {
      d.__proto__ = b;
    } || function (d, b) {
      for (var p in b) {
        if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
      }
    };

    return _extendStatics(d, b);
  };

  return function (d, b) {
    if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");

    _extendStatics(d, b);

    function __() {
      this.constructor = d;
    }

    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
  };
}();

var __awaiter = this && this.__awaiter || function (thisArg, _arguments, P, generator) {
  function adopt(value) {
    return value instanceof P ? value : new P(function (resolve) {
      resolve(value);
    });
  }

  return new (P || (P = Promise))(function (resolve, reject) {
    function fulfilled(value) {
      try {
        step(generator.next(value));
      } catch (e) {
        reject(e);
      }
    }

    function rejected(value) {
      try {
        step(generator["throw"](value));
      } catch (e) {
        reject(e);
      }
    }

    function step(result) {
      result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected);
    }

    step((generator = generator.apply(thisArg, _arguments || [])).next());
  });
};

var __generator = this && this.__generator || function (thisArg, body) {
  var _ = {
    label: 0,
    sent: function sent() {
      if (t[0] & 1) throw t[1];
      return t[1];
    },
    trys: [],
    ops: []
  },
      f,
      y,
      t,
      g;
  return g = {
    next: verb(0),
    "throw": verb(1),
    "return": verb(2)
  }, typeof Symbol === "function" && (g[Symbol.iterator] = function () {
    return this;
  }), g;

  function verb(n) {
    return function (v) {
      return step([n, v]);
    };
  }

  function step(op) {
    if (f) throw new TypeError("Generator is already executing.");

    while (_) {
      try {
        if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
        if (y = 0, t) op = [op[0] & 2, t.value];

        switch (op[0]) {
          case 0:
          case 1:
            t = op;
            break;

          case 4:
            _.label++;
            return {
              value: op[1],
              done: false
            };

          case 5:
            _.label++;
            y = op[1];
            op = [0];
            continue;

          case 7:
            op = _.ops.pop();

            _.trys.pop();

            continue;

          default:
            if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
              _ = 0;
              continue;
            }

            if (op[0] === 3 && (!t || op[1] > t[0] && op[1] < t[3])) {
              _.label = op[1];
              break;
            }

            if (op[0] === 6 && _.label < t[1]) {
              _.label = t[1];
              t = op;
              break;
            }

            if (t && _.label < t[2]) {
              _.label = t[2];

              _.ops.push(op);

              break;
            }

            if (t[2]) _.ops.pop();

            _.trys.pop();

            continue;
        }

        op = body.call(thisArg, _);
      } catch (e) {
        op = [6, e];
        y = 0;
      } finally {
        f = t = 0;
      }
    }

    if (op[0] & 5) throw op[1];
    return {
      value: op[0] ? op[1] : void 0,
      done: true
    };
  }
};

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});

var view_1 = __importDefault(require("../core/view"));

var api_1 = require("../core/api");

var config_1 = require("../config");

var SellListView =
/** @class */
function (_super) {
  __extends(SellListView, _super);

  function SellListView(containerId) {
    var _this = _super.call(this) || this;

    var containerElement = document.getElementById(containerId);

    if (!containerElement) {
      throw 'sell-list 컨테이너가 없습니다.';
    }

    _this.sellListContainer = containerElement;
    _this.listOptions = {
      page: 1,
      amount: 20,
      categoryId: '',
      locationId: '',
      sortOption: 'modifiedDate'
    };
    return _this;
  }

  SellListView.prototype.addEvent = function () {};

  SellListView.prototype.render = function () {
    return __awaiter(this, void 0, void 0, function () {
      var api, data;

      var _this = this;

      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            api = new api_1.SellListApi(config_1.PRODUCT_LIST_URL.replace('@type', 'talent-sell').replace('@page', String(this.listOptions.page)).replace('@amount', String(this.listOptions.amount)).replace('@category', this.listOptions.categoryId).replace('@location', this.listOptions.locationId).replace('@sort', this.listOptions.sortOption));
            return [4
            /*yield*/
            , api.getData()];

          case 1:
            data = _a.sent();
            data.forEach(function (item) {
              var productId = item.productId,
                  title = item.title,
                  image = item.image,
                  price = item.price,
                  reviewCount = item.reviewCount,
                  averageStarScore = item.averageStarScore;

              _this.addHtml("\n            <a href=\"/product/detail/" + productId + "\" class=\"sell-list-content\">\n                <div class=\"img-wrapper\"><img src=\"" + image + "\" alt=\"\uCEE8\uD150\uCE20 \uC774\uBBF8\uC9C0\"></div>\n                <div class=\"sell-list-content-title\">" + title + "</div>\n                <div class=\"sell-list-content-star\">\u2B50\uFE0F " + averageStarScore + " |</div>\n                <div class=\"sell-list-content-review\">" + reviewCount + "\uAC1C\uC758 \uD3C9\uAC00</div>\n                <div class=\"sell-list-content-price\">" + price + "\uC6D0</div>\n            </a>\n            ");
            });
            this.sellListContainer.innerHTML = this.getHtml();
            return [2
            /*return*/
            ];
        }
      });
    });
  };

  return SellListView;
}(view_1.default);

exports.default = SellListView;
},{"../core/view":"yLDq","../core/api":"WIJd","../config":"M5zi"}],"IntY":[function(require,module,exports) {
"use strict";

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});

var router_1 = __importDefault(require("./core/router"));

var page_1 = require("./page");

var sell_list_view_1 = __importDefault(require("./page/sell-list-view"));

var routeUrl = window.location.pathname;
console.log(routeUrl);
var router = new router_1.default(routeUrl);

if (routeUrl.indexOf('/home') >= 0) {
  var homeView = new page_1.HomeView();
  router.addRoutePath('/home', homeView);
} else if (routeUrl.indexOf('/product/detail/') >= 0) {
  var productDetailView = new page_1.ProductDetailView('large-image');
  router.addRoutePath('/product/detail/', productDetailView);
} else if (routeUrl.indexOf('/product/talent-sell/list/') >= 0) {
  var sellListView = new sell_list_view_1.default('sell-list-container');
  router.addRoutePath('/product/talent-sell/list/', sellListView);
}

router.route();
},{"./core/router":"FOel","./page":"il3j","./page/sell-list-view":"LhbK"}]},{},["IntY"], null)
//# sourceMappingURL=/app.652c5242.js.map