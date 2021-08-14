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
      return location.href = '/';
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

  View.prototype.categoryConvertor = function (categort) {
    switch (categort) {
      case '전체':
        return '';

      case '디자인':
        return '01';

      case 'IT프로그래밍':
        return '02';

      case '통역 번역':
        return '03';

      case '마케팅':
        return '04';

      case '영상/편집':
        return '05';

      case '레슨':
        return '06';

      case '스포츠':
        return '07';

      case '문서작업':
        return '08';

      default:
        return '';
    }
  };

  View.prototype.locationConvertor = function (categort) {
    switch (categort) {
      case '전체':
        return '';

      case '서울':
        return '01';

      case '부산':
        return '02';

      case '대구':
        return '03';

      case '인천':
        return '04';

      case '광주':
        return '05';

      case '대전':
        return '06';

      case '울산':
        return '07';

      case '세종':
        return '08';

      case '경기':
        return '09';

      case '강원':
        return '10';

      case '충청북도':
        return '11';

      case '충청남도':
        return '12';

      case '전라북도':
        return '13';

      case '전라남도':
        return '14';

      case '경상북도':
        return '15';

      case '경상남도':
        return '16';

      case '제주':
        return '17';

      default:
        return '';
    }
  };

  View.prototype.sortConvertor = function (option) {
    switch (option) {
      case '최신순':
        return 'modifiedDate';

      case '가격 높은 순':
        return 'expensive';

      case '가격 낮은 순':
        return 'cheap';

      case '평점 높은 순':
        return 'review';

      default:
        return 'modifiedDate';
    }
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

    if (!containerElement) {
      throw 'image 컨테이너가 없습니다.';
    }

    _this.imageContainer = containerElement;

    _this.addEvent();

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

  ProductDetailView.prototype.render = function () {};

  return ProductDetailView;
}(view_1.default);

exports.default = ProductDetailView;
},{"../core/view":"yLDq"}],"WIJd":[function(require,module,exports) {
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

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.ImagesApi = exports.ProductPostApi = exports.SellSaveApi = exports.SellListApi = void 0;

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

  Api.prototype.saveRequest = function (body) {
    return __awaiter(this, void 0, Promise, function () {
      var response;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            return [4
            /*yield*/
            , fetch(this.url, {
              method: 'POST',
              body: body
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

var SellSaveApi =
/** @class */
function (_super) {
  __extends(SellSaveApi, _super);

  function SellSaveApi(url) {
    return _super.call(this, url) || this;
  }

  SellSaveApi.prototype.saveData = function (formData) {
    return __awaiter(this, void 0, void 0, function () {
      return __generator(this, function (_a) {
        return [2
        /*return*/
        , this.saveRequest(formData)];
      });
    });
  };

  return SellSaveApi;
}(Api);

exports.SellSaveApi = SellSaveApi;

var ProductPostApi =
/** @class */
function (_super) {
  __extends(ProductPostApi, _super);

  function ProductPostApi(url) {
    return _super.call(this, url) || this;
  }

  ProductPostApi.prototype.getData = function () {
    return __awaiter(this, void 0, Promise, function () {
      return __generator(this, function (_a) {
        return [2
        /*return*/
        , this.getRequest()];
      });
    });
  };

  return ProductPostApi;
}(Api);

exports.ProductPostApi = ProductPostApi;

var ImagesApi =
/** @class */
function (_super) {
  __extends(ImagesApi, _super);

  function ImagesApi(url) {
    return _super.call(this, url) || this;
  }

  ImagesApi.prototype.getData = function () {
    return __awaiter(this, void 0, Promise, function () {
      return __generator(this, function (_a) {
        return [2
        /*return*/
        , this.getRequest()];
      });
    });
  };

  return ImagesApi;
}(Api);

exports.ImagesApi = ImagesApi;
},{}],"M5zi":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.PRODUCT_IMAGE_URL = exports.PRODUCT_POST_URL = exports.PRODUCT_SAVE_URL = exports.PRODUCT_LIST_URL = void 0;
exports.PRODUCT_LIST_URL = 'http://localhost:8080/api/product/@type/list/page/@page/amount/@amount?category=@category&location=@location&sort=@sort';
exports.PRODUCT_SAVE_URL = 'http://localhost:8080/api/product';
exports.PRODUCT_POST_URL = 'http://localhost:8080/api/product/@productId/post';
exports.PRODUCT_IMAGE_URL = 'http://localhost:8080/api/product/@productId/images';
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

  function SellListView(containerId, amountId) {
    var _this = _super.call(this) || this;

    var containerElement = document.getElementById(containerId);
    var amountElement = document.getElementById(amountId);

    if (!containerElement || !amountElement) {
      throw 'sell-list 컨테이너가 없습니다.';
    }

    _this.sellListContainer = containerElement;
    _this.productAmount = amountElement;
    _this.listOptions = {
      page: 1,
      amount: 20,
      categoryId: '',
      locationId: '',
      sortOption: 'modifiedDate'
    };

    _this.addEvent();

    console.log('sell-list-view');
    return _this;
  }

  SellListView.prototype.addEvent = function () {
    this.categoryEvent();
    this.locationEvent();
    this.searchOptionEvent();
    this.viewOptionEvent();
  };

  SellListView.prototype.categoryEvent = function () {
    var _this = this;

    var items = document.querySelectorAll('.category-item');
    items.forEach(function (item) {
      return item.addEventListener('click', function () {
        if (item.classList.contains('category-choice')) {
          return;
        }

        var items = document.querySelectorAll('.category-item');
        items.forEach(function (item) {
          return item.classList.remove('category-choice');
        });
        item.classList.add('category-choice');
        _this.listOptions.categoryId = _this.categoryConvertor(item.innerText);
      });
    });
  };

  SellListView.prototype.locationEvent = function () {
    var _this = this;

    var item = document.getElementById('location-city');
    item.addEventListener('change', function () {
      return _this.listOptions.locationId = _this.locationConvertor(item.options[item.selectedIndex].text);
    });
  };

  SellListView.prototype.searchOptionEvent = function () {
    var _this = this;

    var applyBtn = document.getElementById('search-option-apply-button');

    if (applyBtn) {
      applyBtn.addEventListener('click', function () {
        return _this.render();
      });
    }
  };

  SellListView.prototype.viewOptionEvent = function () {
    var _this = this;

    var items = document.querySelectorAll('.sort-option-item');
    items.forEach(function (item) {
      return item.addEventListener('click', function () {
        var itemClasses = item.classList;

        if (itemClasses.contains('sort-choice')) {
          return;
        }

        var items = document.querySelectorAll('.sort-option-item');
        items.forEach(function (item) {
          return item.classList.remove('sort-choice');
        });
        itemClasses.add('sort-choice');
        _this.listOptions.sortOption = _this.sortConvertor(item.innerText);

        _this.render();
      });
    });
  };

  SellListView.prototype.render = function () {
    return __awaiter(this, void 0, void 0, function () {
      var api, data, i;

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

            if (data.length % 4 != 0) {
              for (i = 0; i < data.length % 4; i++) {
                this.addHtml("<div class=\"sell-list-content\"></div>");
              }
            }

            this.sellListContainer.innerHTML = this.getHtml();
            this.productAmount.innerHTML = data.length + "\uAC1C\uC758 \uAC8C\uC2DC\uBB3C";
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
},{"../core/view":"yLDq","../core/api":"WIJd","../config":"M5zi"}],"cmOb":[function(require,module,exports) {
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

var SellPostView =
/** @class */
function (_super) {
  __extends(SellPostView, _super);

  function SellPostView() {
    var _this = _super.call(this) || this;

    _this.api = new api_1.SellSaveApi(config_1.PRODUCT_SAVE_URL);
    console.log("sell-post");
    _this.talentSellSaveData = {
      userId: Number(sessionStorage.getItem('userId')),
      title: null,
      categoryId: null,
      serviceInformation: null,
      images: null,
      price: null,
      type: 'talent-sell'
    };

    _this.addEvent();

    return _this;
  }

  SellPostView.prototype.addEvent = function () {
    this.titleEvent();
    this.serviceInformationEvent();
    this.imageEvent();
    this.cancleImageEvent();
    this.submitEvent();
  };

  SellPostView.prototype.saveDocument = function () {
    var userId = Number(sessionStorage.getItem('userId'));
    var title = document.getElementById('title');
    var category = document.getElementById('category-list');
    var serviceInformation = document.getElementById('service-information');
    var imageItems = document.querySelectorAll('.images');
    var isSaved = document.querySelectorAll('.isSaved');
    var price = document.getElementById('price');
    var imageData = [];
    var order = 0;

    for (var i = 0; i < imageItems.length; i++) {
      if (isSaved[i].value === 'true' || isSaved[i].value === 'false') {
        imageData.push({
          file: imageItems[i].files,
          isSaved: isSaved[i].value,
          order: order
        });
        order++;
      }
    }

    order = 0;
    return {
      userId: userId,
      title: title.value,
      categoryId: this.categoryConvertor(category.value),
      serviceInformation: serviceInformation.value,
      images: imageData,
      price: Number(price.value),
      type: 'talent-sell'
    };
  };

  SellPostView.prototype.titleEvent = function () {
    var titleElement = document.getElementById('title');

    if (titleElement) {
      titleElement.addEventListener('input', function () {
        var titleLength = document.getElementById('title-length');

        if (titleLength) {
          titleLength.innerHTML = String(titleElement.value.length);
        }
      });
    }
  };

  SellPostView.prototype.serviceInformationEvent = function () {
    var informationElement = document.getElementById('service-information');

    if (informationElement) {
      informationElement.addEventListener('input', function () {
        var informationLength = document.getElementById('service-information-length');

        if (informationLength) {
          informationLength.innerHTML = String(informationElement.value.length);
        }
      });
    }
  };

  SellPostView.prototype.imageEvent = function () {
    var _this = this;

    var first = this.imageObject('first-image', 'first-image-repository', 0);
    first.container.addEventListener('click', function () {
      first.repository.click();
    });
    first.repository.addEventListener('change', function (file) {
      return _this.imageChangeEvent(file, first.container, first.repository, first.isSaved, first.cancleButton);
    });
    var second = this.imageObject('second-image', 'second-image-repository', 1);
    second.container.addEventListener('click', function () {
      second.repository.click();
    });
    second.repository.addEventListener('change', function (file) {
      return _this.imageChangeEvent(file, second.container, second.repository, second.isSaved, second.cancleButton);
    });
    var third = this.imageObject('third-image', 'third-image-repository', 2);
    third.container.addEventListener('click', function () {
      third.repository.click();
    });
    third.repository.addEventListener('change', function (file) {
      return _this.imageChangeEvent(file, third.container, third.repository, third.isSaved, third.cancleButton);
    });
    var fourth = this.imageObject('fourth-image', 'fourth-image-repository', 3);
    fourth.container.addEventListener('click', function () {
      fourth.repository.click();
    });
    fourth.repository.addEventListener('change', function (file) {
      return _this.imageChangeEvent(file, fourth.container, fourth.repository, fourth.isSaved, fourth.cancleButton);
    });
    var fifth = this.imageObject('fifth-image', 'fifth-image-repository', 4);
    fifth.container.addEventListener('click', function () {
      fifth.repository.click();
    });
    fifth.repository.addEventListener('change', function (file) {
      return _this.imageChangeEvent(file, fifth.container, fifth.repository, fifth.isSaved, fifth.cancleButton);
    });
  };

  SellPostView.prototype.imageObject = function (containerId, repositoryId, index) {
    var container = document.getElementById(containerId);
    var repository = document.getElementById(repositoryId);
    var isSaved = document.querySelectorAll('.isSaved');
    var cancleButtons = document.querySelectorAll('.sell-post-image-cancel-button');
    return {
      container: container,
      repository: repository,
      isSaved: isSaved[index],
      cancleButton: cancleButtons[index]
    };
  };

  SellPostView.prototype.imageChangeEvent = function (image, container, repository, isSaved, cancleButton) {
    if (image) {
      var reader_1 = new FileReader();
      reader_1.addEventListener("load", function () {
        container.style.backgroundImage = "url(" + reader_1.result + ")";
        isSaved.value = 'false';
        cancleButton.classList.remove('visibility-hidden');
      }, false);

      if (repository.files) {
        reader_1.readAsDataURL(repository.files[0]);
      }
    }
  };

  SellPostView.prototype.cancleImageEvent = function () {
    var cancleButtons = document.querySelectorAll('.sell-post-image-cancel-button');
    var isSaved = document.querySelectorAll('.isSaved');
    var imageContainers = document.querySelectorAll('.image-container');
    var images = document.querySelectorAll('.images');

    var _loop_1 = function _loop_1(i) {
      cancleButtons[i].addEventListener('click', function (event) {
        imageContainers[i].style.backgroundImage = '';
        images[i].value = '';
        isSaved[i].value = '';
        cancleButtons[i].classList.add('visibility-hidden');
        event.stopPropagation();
      });
    };

    for (var i = 0; i < cancleButtons.length; i++) {
      _loop_1(i);
    }
  };

  SellPostView.prototype.submitEvent = function () {
    var _this = this;

    var submitButton = document.getElementById('submit');
    submitButton.addEventListener('click', function () {
      var _a, _b;

      _this.talentSellSaveData = _this.saveDocument();
      console.log(_this.talentSellSaveData);
      var formData = new FormData();
      formData.append('userId', String(_this.talentSellSaveData.userId));
      formData.append('title', _this.talentSellSaveData.title);
      formData.append('categoryId', _this.talentSellSaveData.categoryId);
      formData.append('serviceInformation', _this.talentSellSaveData.serviceInformation);
      formData.append('price', String(_this.talentSellSaveData.price));
      formData.append('type', _this.talentSellSaveData.type);

      if (((_a = _this.talentSellSaveData.images) === null || _a === void 0 ? void 0 : _a.length) > 0) {
        formData.append("images", "");
        var imageInfo = JSON.stringify({
          isSaved: "dummy",
          order: "dummy"
        });
        formData.append("imageInformation", imageInfo);
      }

      (_b = _this.talentSellSaveData.images) === null || _b === void 0 ? void 0 : _b.forEach(function (data) {
        console.log(data);
        formData.append("images", data.file[0]);
        var imageInfo = JSON.stringify({
          isSaved: data.isSaved,
          order: String(data.order)
        });
        formData.append("imageInformation", imageInfo);
        console.log(formData.get('imageInformation'));
      });
      console.log("imageInformation : " + formData.get('imageInformation'));

      _this.saveData(formData);
    });
  };

  SellPostView.prototype.saveData = function (formData) {
    return __awaiter(this, void 0, void 0, function () {
      var result;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            return [4
            /*yield*/
            , this.api.saveData(formData)];

          case 1:
            result = _a.sent();

            if (result.status === 1) {
              alert('성공');
            } else {
              alert(result.json);
            }

            return [2
            /*return*/
            ];
        }
      });
    });
  };

  SellPostView.prototype.render = function () {
    return __awaiter(this, void 0, void 0, function () {
      var productId, productPostApi, ProductPostData, title, category, serviceInformation, price, imageApi, ImageData_1, cancleButtons, isSaved, imageContainers, i;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            if (!(location.search != '')) return [3
            /*break*/
            , 3];
            productId = location.search.substr(9);
            console.log(productId);
            productPostApi = new api_1.ProductPostApi(config_1.PRODUCT_POST_URL.replace('@productId', String(productId)));
            return [4
            /*yield*/
            , productPostApi.getData()];

          case 1:
            ProductPostData = _a.sent();

            if (ProductPostData) {
              title = document.getElementById('title');
              title.value = ProductPostData.title;
              category = document.getElementById('category-list');
              category.selectedIndex = ProductPostData.productId;
              serviceInformation = document.getElementById('service-information');
              serviceInformation.value = ProductPostData.serviceInformation;
              price = document.getElementById('price');
              price.value = ProductPostData.price;
            }

            imageApi = new api_1.ImagesApi(config_1.PRODUCT_IMAGE_URL.replace('@productId', String(productId)));
            return [4
            /*yield*/
            , imageApi.getData()];

          case 2:
            ImageData_1 = _a.sent();

            if (ImageData_1.length != 0) {
              cancleButtons = document.querySelectorAll('.sell-post-image-cancel-button');
              isSaved = document.querySelectorAll('.isSaved');
              imageContainers = document.querySelectorAll('.image-container');

              for (i = 0; i < ImageData_1.length; i++) {
                imageContainers[i].style.backgroundImage = "url(" + ImageData_1[i].imageUrl + ")";
                isSaved[i].value = 'true';
                cancleButtons[i].classList.remove('visibility-hidden');
              }
            }

            _a.label = 3;

          case 3:
            return [2
            /*return*/
            ];
        }
      });
    });
  };

  return SellPostView;
}(view_1.default);

exports.default = SellPostView;
},{"../core/view":"yLDq","../core/api":"WIJd","../config":"M5zi"}],"il3j":[function(require,module,exports) {
"use strict";

var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.SellPostView = exports.SellListView = exports.ProductDetailView = exports.HomeView = void 0;

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

var sell_list_view_1 = require("./sell-list-view");

Object.defineProperty(exports, "SellListView", {
  enumerable: true,
  get: function get() {
    return __importDefault(sell_list_view_1).default;
  }
});

var sell_post_view_1 = require("./sell-post-view");

Object.defineProperty(exports, "SellPostView", {
  enumerable: true,
  get: function get() {
    return __importDefault(sell_post_view_1).default;
  }
});
},{"./home-view":"R1dU","./product-detail-view":"zCWJ","./sell-list-view":"LhbK","./sell-post-view":"cmOb"}],"IntY":[function(require,module,exports) {
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

var routeUrl = window.location.pathname;
console.log(routeUrl);
var router = new router_1.default(routeUrl);

if (routeUrl === '/') {
  var homeView = new page_1.HomeView();
  router.addRoutePath('/', homeView);
} else if (routeUrl.indexOf('/product/detail/') >= 0) {
  var productDetailView = new page_1.ProductDetailView('large-image');
  router.addRoutePath('/product/detail/', productDetailView);
} else if (routeUrl.indexOf('/product/talent-sell/list/') >= 0) {
  var sellListView = new page_1.SellListView('sell-list-container', 'amount');
  router.addRoutePath('/product/talent-sell/list/', sellListView);
} else if (routeUrl.indexOf('/product/talent-sell/post') >= 0) {
  var sellPostView = new page_1.SellPostView();
  router.addRoutePath('/product/talent-sell/post', sellPostView);
}

router.route();
},{"./core/router":"FOel","./page":"il3j"}]},{},["IntY"], null)
//# sourceMappingURL=/app.652c5242.js.map