var datepickr = function (selector, config) {
    'use strict';
    var elements,
        createInstance,
        instances = [],
        i;

    datepickr.prototype = datepickr.init.prototype;

    createInstance = function (element) {
        if (element._datepickr) {
            element._datepickr.destroy();
        }
        element._datepickr = new datepickr.init(element, config);
        return element._datepickr;
    };

    if (selector.nodeName) {
        return createInstance(selector);
    }

    elements = document.querySelectorAll(selector);
    if (elements.length === 1) {
        return createInstance(elements[0]);
    }

    for (i = 0; i < elements.length; i++) {
        instances.push(createInstance(elements[i]));
    }
    return instances;
};

/**
 * @constructor
 */
datepickr.init = function (element, instanceConfig) {
    'use strict';
    var self = this,
        defaultConfig = {
            dateFormat: 'F j, Y',
            altFormat: null,
            altInput: null,
            selDate: null,
            rangeFrom: null,
            rangeTo: null,
            minDate: null,
            maxDate: null,
            shorthandCurrentMonth: false
        },
        calendarContainer = document.createElement('div'),
        navigationCurrentMonth = document.createElement('span'),
        calendar = document.createElement('table'),
        calendarBody = document.createElement('tbody'),
        wrapperElement,
        currentDate = new Date(),
        wrap,
        date,
        formatDate,
        monthToStr,
        isSpecificDay,
        buildWeekdays,
        buildDays,
        updateNavigationCurrentMonth,
        buildMonthNavigation,
        handleYearChange,
        documentClick,
        calendarClick,
        calendarChange,
        buildCalendar,
        getOpenEvent,
        bind,
        open,
        position,
        close,
        destroy,
        init;

    calendarContainer.className = 'datepickr-calendar';
    navigationCurrentMonth.className = 'datepickr-current-month';
    instanceConfig = instanceConfig || {};

    wrap = function () {
        wrapperElement = document.createElement('div');
        wrapperElement.className = 'datepickr-wrapper';
        self.element.parentNode.insertBefore(wrapperElement, self.element);
        wrapperElement.appendChild(self.element);
    };

    date = {
        current: {
            year: function () {
                return currentDate.getFullYear();
            },
            month: {
                integer: function () {
                    return currentDate.getMonth();
                },
                string: function (shorthand) {
                    var month = currentDate.getMonth();
                    return monthToStr(month, shorthand);
                }
            },
            day: function () {
                return currentDate.getDate();
            }
        },
        month: {
            string: function () {
                return monthToStr(self.currentMonthView, self.config.shorthandCurrentMonth);
            },
            numDays: function () {
                // checks to see if february is a leap year otherwise return the respective # of days
                return self.currentMonthView === 1 && (((self.currentYearView % 4 === 0) && (self.currentYearView % 100 !== 0)) || (self.currentYearView % 400 === 0)) ? 29 : self.l10n.daysInMonth[self.currentMonthView];
            }
        }
    };

    formatDate = function (dateFormat, milliseconds) {
        var formattedDate = '',
            dateObj = new Date(milliseconds),
            formats = {
                d: function () {
                    var day = formats.j();
                    return (day < 10) ? '0' + day : day;
                },
                D: function () {
                    return self.l10n.weekdays.shorthand[formats.w()];
                },
                j: function () {
                    return dateObj.getDate();
                },
                l: function () {
                    return self.l10n.weekdays.longhand[formats.w()];
                },
                w: function () {
                    return dateObj.getDay();
                },
                F: function () {
                    return monthToStr(formats.n() - 1, false);
                },
                m: function () {
                    var month = formats.n();
                    return (month < 10) ? '0' + month : month;
                },
                M: function () {
                    return monthToStr(formats.n() - 1, true);
                },
                n: function () {
                    return dateObj.getMonth() + 1;
                },
                U: function () {
                    return dateObj.getTime() / 1000;
                },
                y: function () {
                    return String(formats.Y()).substring(2);
                },
                Y: function () {
                    return dateObj.getFullYear();
                }
            },
            formatPieces = dateFormat.split('');

        self.forEach(formatPieces, function (formatPiece, index) {
            if (formats[formatPiece] && formatPieces[index - 1] !== '\\') {
                formattedDate += formats[formatPiece]();
            } else {
                if (formatPiece !== '\\') {
                    formattedDate += formatPiece;
                }
            }
        });

        return formattedDate;
    };

    monthToStr = function (date, shorthand) {
        if (shorthand === true) {
            return self.l10n.months.shorthand[date];
        }

        return self.l10n.months.longhand[date];
    };

    isSpecificDay = function (day, month, year, comparison) {
        return day === comparison && self.currentMonthView === month && self.currentYearView === year;
    };

    buildWeekdays = function () {

        var weekdayContainer = document.createElement('thead'),
            firstDayOfWeek = self.l10n.firstDayOfWeek,
            weekdays = self.l10n.weekdays.shorthand;

        if (firstDayOfWeek > 0 && firstDayOfWeek < weekdays.length) {
            weekdays = [].concat(weekdays.splice(firstDayOfWeek, weekdays.length), weekdays.splice(0, firstDayOfWeek));
        }

        var tr = document.createElement('tr');
        
        for(var i in weekdays) {
            var day = weekdays[i];
            var th = document.createElement('th');
            th.innerHTML = day;
            tr.appendChild(th);
        }

        weekdayContainer.appendChild(tr);
        calendar.appendChild(weekdayContainer);
    };

    buildDays = function () {
       
        var firstOfMonth = new Date(self.currentYearView, self.currentMonthView, 1).getDay(),
            numDays = date.month.numDays(),
            calendarFragment = document.createDocumentFragment(),
            row = document.createElement('tr'),
            dayCount,
            dayNumber,
            today = '',
            selected = '',
            disabled = '',
            currentTimestamp;

        if( self.config['rangeFrom'] ) {
            var objRange = document.getElementById(self.config['rangeFrom']+"_h");
            if( objRange && objRange.value ) {
                self.config.minDate = new Date(objRange.value+'T00:00:00').getTime();
            }
        }

        if( self.config['rangeTo'] ) {
            var objRange = document.getElementById(self.config['rangeTo']+"_h");
            if( objRange && objRange.value ) {
                self.config.maxDate = new Date(objRange.value+'T00:00:00').getTime();
            }
        }

        // Offset the first day by the specified amount
        firstOfMonth -= self.l10n.firstDayOfWeek;
        if (firstOfMonth < 0) {
            firstOfMonth += 7;
        }

        dayCount = firstOfMonth;
        while (calendarBody.firstChild) {
            calendarBody.removeChild(calendarBody.firstChild);
        }
        
        // Add spacer to line up the first day of the month correctly
        if (firstOfMonth > 0) {
            var td = document.createElement('td');
            td.setAttribute('colspan',firstOfMonth);
            td.innerHTML = '&nbsp;';
            row.appendChild(td);
        }

        // Start at 1 since there is no 0th day
        for (dayNumber = 1; dayNumber <= numDays; dayNumber++) {
            // if we have reached the end of a week, wrap to the next line
            if (dayCount === 7) {
                calendarFragment.appendChild(row);
                row = document.createElement('tr');
                dayCount = 0;
            }

            today = isSpecificDay(date.current.day(), date.current.month.integer(), date.current.year(), dayNumber) ? ' today' : '';
            if (self.selectedDate) {
                selected = isSpecificDay(self.selectedDate.day, self.selectedDate.month, self.selectedDate.year, dayNumber) ? ' selected' : '';
            }

            if (self.config.minDate || self.config.maxDate) {
                currentTimestamp = new Date(self.currentYearView, self.currentMonthView, dayNumber).getTime();
                disabled = '';

                if (self.config.minDate && currentTimestamp < self.config.minDate) {
                    disabled = ' disabled';
                }

                if (self.config.maxDate && currentTimestamp > self.config.maxDate) {
                    disabled = ' disabled';
                }
            }

            var td = document.createElement('td');
            td.setAttribute('class',today + selected + disabled);
            td.innerHTML = '<span class="datepickr-day">' + dayNumber + '</span>';
            row.appendChild(td);

            dayCount++;
        }


        calendarFragment.appendChild(row);

        calendarBody.appendChild(calendarFragment);

        //alert(calendarBody.innerHTML);

    };

    updateNavigationCurrentMonth = function () {
        
        var year = (new Date).getFullYear();
        var strY = '',strM = '';
        
        for(var i=self.config['yearFrom']; i<=self.config['yearTo']; i++) {
            var sel = i == self.currentYearView ? 'selected' : '';
            strY += '<option value="'+i+'" '+sel+'>'+i;
        }
        for( var i in self.l10n.months.longhand ) {
            var sel = i == self.currentMonthView ? 'selected' : '';
            strM += '<option value="'+i+'" '+sel+'>'+self.l10n.months.longhand[i];
        }

        var str =   '<div class="datepickr-sel-date">'+
                        '<div class="datepickr-sel-arrow"></div>'+
                        '<select class="datepickr-sel-year">'+strY+'</select>'+
                    '</div>'+
                    '<div class="datepickr-sel-date">'+
                        '<div class="datepickr-sel-arrow"></div>'+
                        '<select class="datepickr-sel-month">'+strM+'</select>'+
                    '</div>';

        navigationCurrentMonth.innerHTML = str;

        var selYear  = navigationCurrentMonth.querySelector('.datepickr-sel-year');
        var selMonth = navigationCurrentMonth.querySelector('.datepickr-sel-month');
        self.addEventListener(selYear, 'change', calendarChange, false);
        self.addEventListener(selMonth, 'change', calendarChange, false);
        
    };

    buildMonthNavigation = function () {
        var months = document.createElement('div'),
            monthNavigation;

        monthNavigation  = '<span class="datepickr-prev-month">&lt;</span>';
        monthNavigation += '<span class="datepickr-next-month">&gt;</span>';

        months.className = 'datepickr-months';
        months.innerHTML = monthNavigation;

        months.appendChild(navigationCurrentMonth);
        updateNavigationCurrentMonth();
        calendarContainer.appendChild(months);
    };

    handleYearChange = function () {
        if (self.currentMonthView < 0) {
            self.currentYearView--;
            self.currentMonthView = 11;
        }

        if (self.currentMonthView > 11) {
            self.currentYearView++;
            self.currentMonthView = 0;
        }
    };

    documentClick = function (event) {
        var target = event.target || event.srcElement;
        var parent;
        if (target !== self.element && target !== wrapperElement) {
            parent = target.parentNode;
            if (parent !== wrapperElement) {
                while (parent !== wrapperElement) {
                    parent = parent.parentNode;
                    if (parent === null) {
                        close();
                        break;
                    }
                }
            }
        }
    };

    calendarChange = function (event) {
        var target = event.target || event.srcElement;
        var targetClass = target.className;
        if( targetClass ) {
            if( targetClass === 'datepickr-sel-year' ) {
                self.currentYearView = target.value;
            }
            if( targetClass === 'datepickr-sel-month' ) {
                self.currentMonthView = target.value;
            }
        }
        handleYearChange();
        buildDays();
    };

    calendarClick = function (event) {
        
        var target = event.target || event.srcElement,
            targetClass = target.className,
            currentTimestamp;
        
        if (targetClass) {
            if (targetClass === 'datepickr-prev-month' || targetClass === 'datepickr-next-month') {
                if (targetClass === 'datepickr-prev-month') {
                    self.currentMonthView--;
                } else {
                    self.currentMonthView++;
                }

                handleYearChange();
                updateNavigationCurrentMonth();
                buildDays();
            } else if (targetClass === 'datepickr-day' && !self.hasClass(target.parentNode, 'disabled')) {
                self.selectedDate = {
                    day: parseInt(target.innerHTML, 10),
                    month: self.currentMonthView,
                    year: self.currentYearView
                };

                currentTimestamp = new Date(self.currentYearView, self.currentMonthView, self.selectedDate.day).getTime();

                if (self.config.altInput) {
                    if (self.config.altFormat) {
                        self.config.altInput.value = formatDate(self.config.altFormat, currentTimestamp);
                    } else {
                        // I don't know why someone would want to do this... but just in case?
                        self.config.altInput.value = formatDate(self.config.dateFormat, currentTimestamp);
                    }
                }

                self.element.value = formatDate(self.config.dateFormat, currentTimestamp);
                self.element.focus();

                close();
            }
        }
    };

    buildCalendar = function () {
        buildMonthNavigation();
        buildWeekdays();
        buildDays();

        calendar.appendChild(calendarBody);
        calendarContainer.appendChild(calendar);

        wrapperElement.appendChild(calendarContainer);
    };

    getOpenEvent = function () {
        return 'click';
    };

    bind = function () {
        self.addEventListener(self.element, getOpenEvent(), open, false);
        self.addEventListener(calendarContainer, 'click', calendarClick, false);
        
    };

    open = function () {
        buildDays();
        self.addEventListener(document, 'click', documentClick, false);
        self.addClass(wrapperElement, 'open');
        position();
    };

    position = function() {
        calendarContainer.style.top = '-'+calendarContainer.offsetHeight+'px';
    };

    close = function () {
        self.removeEventListener(document, 'click', documentClick, false);
        self.removeEventListener(document, 'change', documentClick, false);
        self.removeClass(wrapperElement, 'open');
    };

    destroy = function () {
        var parent,
            element;

        self.removeEventListener(document, 'click', documentClick, false);
        self.removeEventListener(self.element, getOpenEvent(), open, false);

        parent = self.element.parentNode;
        parent.removeChild(calendarContainer);
        element = parent.removeChild(self.element);
        parent.parentNode.replaceChild(element, parent);
    };

    init = function () {
        var config,
            parsedDate;

        self.config = {};
        self.destroy = destroy;

        for( config in defaultConfig ) {
             self.config[config] = instanceConfig[config] || defaultConfig[config];
        }

        switch(self.config['selDate']) {
            case 'date_past':   self.config['maxDate'] = new Date().getTime();
                                self.config['yearFrom'] = new Date().getFullYear() - 80;
                                self.config['yearTo'] = new Date().getFullYear();
                                break;

            case 'date_future': self.config['minDate'] = new Date().getTime();
                                self.config['yearFrom'] = new Date().getFullYear();
                                self.config['yearTo'] = new Date().getFullYear()+5;
                                break;
        }

        self.element = element;

        if (self.element.value) {
            parsedDate = Date.parse(self.element.value);
        }

        if (parsedDate && !isNaN(parsedDate)) {
            parsedDate = new Date(parsedDate);
            self.selectedDate = {
                day: parsedDate.getDate(),
                month: parsedDate.getMonth(),
                year: parsedDate.getFullYear()
            };
            self.currentYearView = self.selectedDate.year;
            self.currentMonthView = self.selectedDate.month;
            self.currentDayView = self.selectedDate.day;
        } else {
            self.selectedDate = null;
            self.currentYearView = date.current.year();
            self.currentMonthView = date.current.month.integer();
            self.currentDayView = date.current.day();
        }

        wrap();
        buildCalendar();
        bind();
    };

    init();

    return self;
};

datepickr.init.prototype = {
    hasClass: function (element, className) { return new RegExp('(\\s|^)'+className+'(\\s|$)').test(element.className); },
    addClass: function (element, className) { element.className = className; },
    removeClass: function (element, className) { element.className = ''; },
    forEach: function (items, callback) { if(!Array.prototype.forEach ) {
                                              Array.prototype.forEach = function(fn, scope) {
                                                for(var i = 0, len = this.length; i < len; ++i) {
                                                  fn.call(scope, this[i], i, this);
                                                }
                                              }
                                          } else {
                                              [].forEach.call(items, callback);
                                          }
             },
    isArray: Array.isArray,
    addEventListener: function (element, type, listener, useCapture) {
        if( element == null ) return;
        if( element.addEventListener) {
            element.addEventListener(type, listener, useCapture);
        } else if (element.attachEvent) {
            element.attachEvent('on' + type, listener);
        } else {
            element['on' + type] = listener;
        }
    },
    removeEventListener: function (element, type, listener, useCapture) {
        if (element.removeEventListener) {
            element.removeEventListener(type,listener,useCapture);
        } else if (element.detachEvent) {
            element.detachEvent ('on'+type,listener); 
        }
    },
    l10n: {
        weekdays: {
            shorthand: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            longhand: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
        },
        months: {
            shorthand: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            longhand: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
        },
        daysInMonth: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
        firstDayOfWeek: 0
    }
};function contactForm(params) {
	
	var _this = this;

	/*** init ******/
	this.init = function() {
		this.setVars();
		this.setEvents();
		this.setInputs();
	};

	/*** setVars ******/
	this.setVars = function() {
		
		this.r 		= params;
		this._doc 	= document;
		this._body 	= this._doc.getElementsByTagName("body")[0];
		this._ckey  = this.getParameterByName("sdid") || this.getParameterByName("curl") || this.getParameterByName("tid") || this.getParameterByName("did");

		this._objContact     = this.gEBId('contact');
		this._objFormInquery = this.gEBId('contactFormInquery');
		this._objInputs 	 = this.gEBId('contactInputs');
		this._objDivErr      = this.gEBId('contactErr');
		this._objDivErrText  = this.gEBId('contactErrText');
		this._labels		 = [];

		this.psize = {'width':params['width'],'height':params['height']};
	};

	/*** setEvents ******/
	this.setEvents = function() {
		
		this.addEvent( this._objFormInquery, 'focus', this.formExpand );
		this.addEvent( window,'load' ,this.formLoaded);
		this.addEvent( window,'message', this.selfGetMessage );
		
	};

	/*** formLoaded ******/
	this.formLoaded = function() {
		_this.parentLoadInfo();
		_this.parentInvInit();
		_this.parentResize();
		_this.parentInvAuto();
	};

	/*** parentLoadInfo ******/
	this.parentLoadInfo = function() {
		var data = { 'action': 	'loadinfo',
					 'hinfo':    params['hinfo'],
					 'comment':  params['comment']
				   };
		this.parentSendMessage(data);
	};

	/*** parentInvInit ******/
	this.parentInvInit = function() {
		var data = { 'action': 	'invexec' };
		this.parentSendMessage(data);
	};

	/*** parentResize ******/
	this.parentResize = function(m) {
		var m = m || [];
		var size = this.getWindowSize();
		var width  = m['width'] || this.psize['width'] || size['width'];
		var height = m['height'] || this.psize['height'] || size['height'];
		//alert(width+":"+height);
		var data = { 'action': 	'resize',
					 'width': 	width,
					 'height': 	height
				   };
		this.parentSendMessage(data);
	};

	/*** parentInvAuto ******/
	this.parentInvAuto = function() {
		if(this.getCookie('invauto')) return;
		var w = params['expand'] * 1000;
		window.setTimeout(function(){
			if( typeof _contactChatLoad == 'object' ){
				_contactChatLoad.chatInitAuto();
			}
			var data = { 'action': 'invauto',
						 'sound':  true
					   };
			_this.parentSendMessage(data);
			_this.setCookie('invauto',true);
		 },w);
	};

	/*** parentSendMessage ******/
	this.parentSendMessage = function(data) {
		if(!(pdom = this.getParameterByName("pdom"))) return;
		var data = this.jsonEncode(data);
		parent.postMessage(data, pdom );
	};

	/*** selfGetMessage ******/
	this.selfGetMessage = function(event) {
		var m = _this.jsonDecode(event.data);
		switch(m['action']) {
			case 'set_msi': _this.selfSetMsi(m);break;
		}
	};

	/*** selfSetMsi ******/
	this.selfSetMsi = function(m) {
		if(!(obj=this._doc.getElementById('lbDialogText'))) return;
		obj.innerHTML = m['msi'];
		var data = { 'action': 	'loadinfo',
					 'comment': m['msi'],
					 'invsize': this.getWindowSize()
				   };
		_this.parentSendMessage(data);
	};

	/*** formExpand ******/
	this.formExpand = function() {
		
		if( _this._objContact.classList.contains('expanded') ) return;
		if( _this._objInputs) {
			_this._objInputs.style.display = 'table-row-group';
		}
		if( _this.r['jsinp'].length > 1 ) {
			_this._objContact.classList.add('expanded');
			_this._objContact.classList.remove('contact_f2');
			_this.parentResize({'width':380});
		} else {
			_this._objContact.classList.remove('expanded');
			_this.parentResize();
		}
	};
	
	/******* setInputs *******/
	this.setInputs = function() {
		if(!this.r['jsinp']) return;
		for(var i in this.r['jsinp']) {
			if(!(r = this.r['jsinp'][i])) continue;
			if(!(obj = this._doc.getElementById(r['id']))) continue;
			if((label = this._doc.getElementById(r['lid']))) {
				this._labels[r['id']] = label;
				if( obj.value!='' ) {
					label.style.display = 'none';
				}
			}
			this.addEvent( obj , 'focus' , this.inputFocus );
			this.addEvent( obj , 'blur' , this.inputBlur );
			this.addEvent( obj , 'mousedown' , this.inputClick );	
		}
	};

	/******* inputClick *******/
	this.inputClick = function () {
		_this.inpClick = this;
	};

	/******* inputFocus *******/
	this.inputFocus = function (event) {
		var target = event.target || event.srcElement;
		_this.errHide();
		if((label = _this._labels[target.id])) {
			label.style.display = 'none';
		}
		if( target.tagName == 'SELECT' && target != _this.inpClick ) {
			_this.openSelect(target);
		}
	};

	/******* inputBlur *******/
	this.inputBlur = function (event) {
		var target = event.target || event.srcElement;
		_this.errHide();
		if( target.value != '' ) return;
		if((label = _this._labels[target.id])) {
			label.style.display = 'block';
		}
	};

	this.openSelect = function(objSel) {
		objSel.focus();
		if (document.createEvent) { 
        	var e = document.createEvent("MouseEvents");
        	e.initMouseEvent("mousedown", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        	objSel.dispatchEvent(e);

    	} else if (objSel.fireEvent) {
        	objSel.fireEvent("onmousedown");
    	}   
	};

	/**** addEvent *****/
	this.addEvent = function (elm, evType, fn, useCapture) {
		if( elm == null ) return;
		if (elm.addEventListener) {
		   elm.addEventListener(evType, fn, useCapture);
		} else if (elm.attachEvent) {
		   elm.attachEvent('on' + evType, fn);
		} else {
		   elm['on' + evType] = fn;
		}
	};

	/******* sendInit *******/
	this.sendInit = function() {
		return this.sendValidate();
	};

	/******* sendValidate *******/
	this.sendValidate = function() {
		if(!this.r['jsinp']) return;
		this.errHide();
		for(var i in this.r['jsinp']) {
			if(!(r = this.r['jsinp'][i])) continue;
			if(!(obj = this._doc.getElementById(r['id']))) continue;
			if(!this.sendValidateInput(r,obj)) {
				this.errShow(r,obj);
				return false;
			}
		}
		return true;
	};	
	
	/******* errShow *******/
	this.errShow = function(r,obj) {

		var msg = this.decodeChars(r['err']);
		this._objDivErrText.innerHTML = msg;

		var p = this.getPosition(obj);
		this.objShow({ 'obj':this._objDivErr,
					   'step': 5
					});
		var h = this._objDivErr.offsetHeight;
		this._objDivErr.style.left = (p[0]+30)+'px';
		this._objDivErr.style.top = (p[1]-(h))+'px';
		
		obj.classList.toggle('lbFormInputErr');
		obj.focus();
		this.errObj = obj;
	};

	/******* errHide *******/
	this.errHide = function() {
		if(!this.errObj) return;
		this.objHide({ 'obj':this._objDivErr,
					   'step': 5
					});
		this.errObj.classList.toggle('lbFormInputErr');
		this.errObj = false;
	};

	/******* sendValidateInput *******/
	this.sendValidateInput = function(r,obj) {
		var str = obj.value;
		switch(r['typ']) {
			case 1: if(!this.sendValidateMinWords(str,r['min'])) return false;
					break;
			case 2: if(!this.sendValidateMinLetters(str,r['min'])) return false;
					break;
			case 3: if(!this.sendValidateEmail(str)) return false;
					break;
			case 4: if(!this.sendValidateCheckbox(obj)) return false;
					break;
		}
		return true;
	};
	
	/******* sendValidateMinWords *******/
	this.sendValidateMinWords = function (str,min) {
		if(!str) return false;
	    if((words = str.match(/\b\w+\b/g)) == null ) return true;
	    if( words.length >= min ) return true;
		return false;
	};
	
	/******* sendValidateMinLetters *******/
	this.sendValidateMinLetters = function (str,min) {
		if(!str) return false;
	    if( str.length >= min ) return true;
		return false;
	};

	/******* sendValidateEmail *******/
	this.sendValidateEmail = function (address) {
		var reg = /^([\w-?&;#~=\.\/]+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?))/i;
   	    if(reg.test(address)) return true;
 		return false;
	};

	/******* sendValidateCheckbox *******/
	this.sendValidateCheckbox = function (obj) {
		if(obj.checked) return true;
		return false;
	};
	
	/*** errFocus ******/
	this.errFocus = function(id) {
		if(!( obj = this._doc.getElementById(id)))
			return;
		obj.focus();
		obj.parentNode.classList.toggle('lbContactInpOut lbContactFocusErr');
	};
	
	/******* gEBId *******/
	this.gEBId = function(id) {
		obj = this._doc.getElementById(id);
		return obj;
	};

	//******** decodeChars ******
	this.decodeChars = function (str) {
	  	var div = document.createElement('div');
	  	div.style.display = 'none';
		var body = document.getElementsByTagName('body')[0];
		div.innerHTML = str;
		body.appendChild(div);
		var str = div.innerHTML;
		body.removeChild(div);
		return str;
	};

	/******* getPosition *******/
	this.getPosition = function (obj) {
		var curleft = curtop = 0;
		if( obj.offsetParent) {
		  	do {
		    	curleft += obj.offsetLeft;
		     	curtop += obj.offsetTop;
		  	} while (obj = obj.offsetParent);
		}
		return [curleft,curtop];
	};

	/******* objShow *******/
	this.objShow = function(m) {
		m['obj'].style.display = 'block';
		m['obj'].style['-webkit-animation'] = 'fadeIn 0.5s';
		m['obj'].style['animation'] = 'fadeIn 0.5s';
		if ( typeof m['func'] ==  'function' ) m['func'].call();
	};
	
	/******* objHide *******/
	this.objHide = function(m) {
		m['obj'].style.display = 'none';
		if ( typeof m['func'] ==  'function' ) m['func'].call();
	};

    /******* objSetAlpha *******/
    this.objSetAlpha = function (obj,alpha) {
        if ( alpha < 0  || alpha > 100 ) return;
        if ( alpha > 0 ) obj.style.display = 'block';
        obj.style.filter = 'alpha(Opacity='+alpha+')';
        var alp = alpha > 99 ? '1' : '0.'+( alpha < 10 ? '0' : '' )+alpha;
        obj.style.opacity = alp;
    };

    /**** getWindowSize ***/
    this.getWindowSize = function(){
        var body = document.body;
        var html = document.documentElement;
        var width  = body.offsetWidth < html.offsetWidth ? body.offsetWidth : body.scrollWidth;
        var height = body.offsetHeight < html.offsetHeight ? body.offsetHeight : body.scrollHeight;
        return {'width':width,'height':height};
    };

     /**** getParameterByName *****/
    this.getParameterByName = function (name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    };

    /**** jsonDecode *****/
	this.jsonDecode = function(data) {
		if( typeof JSON == 'object' && JSON.parse ) {
			var r = JSON.parse(data);
		} else {
			var r = evJSON.decode(data);
		}
		return r;
	};

	/**** jsonEncode *****/
	this.jsonEncode = function(r) {
		if( typeof JSON == 'object' && JSON.stringify ) {
			var data = JSON.stringify(r);
		} else {
			var data = evJSON.encode(r);
		}
		return data;
	};

	//******** setCookie ******
	this.setCookie = function (name,value,days) {
		if(days) {
		  var date = new Date();
		  date.setTime(date.getTime()+(days*24*60*60*1000));
		  var expires = "; expires="+date.toGMTString();
		} else expires = "";
		var value = encodeURIComponent(value);
		document.cookie = name+'['+this._ckey+']'+"="+value+expires+"; path=/";
	};

	//******** getCookie ******
	this.getCookie = function (name) {
		var nameEQ = name+'['+this._ckey+']'+'=';
		var ca = document.cookie.split(';');
		for(var i=0;i<ca.length;i++) {
		  var c = ca[i];
		  while (c.charAt(0)==' ') c = c.substring(1,c.length);
		  if (c.indexOf(nameEQ) == 0) {
			  var value = decodeURIComponent(c.substring(nameEQ.length,c.length));
			  return value;
		  }
		}
		return null;
	};
}

/**** ClassList IE <= 9 ********/

(function () {
if (typeof window.Element === "undefined" || "classList" in document.documentElement) return;
var prototype = Array.prototype,
    push = prototype.push,
    splice = prototype.splice,
    join = prototype.join;

function DOMTokenList(el) {
  this.el = el;
  var classes = el.className.replace(/^\s+|\s+$/g,'').split(/\s+/);
  for (var i = 0; i < classes.length; i++) {
    push.call(this, classes[i]);
  }
};

DOMTokenList.prototype = {
  add: function(token) {
    if(this.contains(token)) return;
    push.call(this, token);
    this.el.className = this.toString();
  },
  contains: function(token) {
    return this.el.className.indexOf(token) != -1;
  },
  item: function(index) {
    return this[index] || null;
  },
  remove: function(token) {
    if (!this.contains(token)) return;
    for (var i = 0; i < this.length; i++) {
      if (this[i] == token) break;
    }
    splice.call(this, i, 1);
    this.el.className = this.toString();
  },
  toString: function() {
    return join.call(this, ' ');
  },
  toggle: function(token) {
    if (!this.contains(token)) {
      this.add(token);
    } else {
      this.remove(token);
    }

    return this.contains(token);
  }
};

window.DOMTokenList = DOMTokenList;
function defineElementGetter (obj, prop, getter) {
    if (Object.defineProperty) {
        Object.defineProperty(obj, prop,{
            get : getter
        });
    } else {
        obj.__defineGetter__(prop, getter);
    }
}

defineElementGetter(Element.prototype, 'classList', function () {
  return new DOMTokenList(this);
});
})();

/**** End of ClassList IE <= 9 ********/
function contactEmailForm(params) {

	_this = this;

	/*** init ******/
	this.init = function() {
		this.setVars();
	};
	
	/*** setVars ******/
	this.setVars = function() {
		this._doc 	= document;
		this._body 	= this._doc.getElementsByTagName("body")[0];
	};
}