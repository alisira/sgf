function qa_mailfollow_set_inner_html(elem, type, html)
{
	elem.innerHTML=html;
}

function qa_mailfollow_show_waiting_after(elem, inside)
{
	if (elem && !elem.qa_waiting_shown) {
		var w=document.getElementById('qa-waiting-template');
	
		if (w) {
			var c=w.cloneNode(true);
			c.id=null;
			
			if (inside)
				elem.insertBefore(c, null);
			else
				elem.parentNode.insertBefore(c, elem.nextSibling);

			elem.qa_waiting_shown=c;
		}
	}
}

function qa_mailfollow_hide_waiting(elem)
{
	var c=elem.qa_waiting_shown;

	if (c) {
		c.parentNode.removeChild(c);
		elem.qa_waiting_shown=null;
	}
}

function qa_mailfollow_click(elem)
{
	var ens=elem.name.split('_');
	var code=elem.form.elements.code.value;
	
//alert(favorite:parseInt(ens[3]));
	qa_mailfollow_ajax_post('mailfollow', {entitytype:ens[1], postid:ens[2], favorite:parseInt(ens[3]), code:code},
		function (response_lines) {
			if (response_lines[0]=='1')
				qa_mailfollow_set_inner_html(document.getElementById('mailfollowing'), 'mailfollowing', response_lines.slice(1).join("\n"));
			else if (response_lines[0]=='0') {
				alert(response_lines[1]);
				qa_mailfollow_hide_waiting(elem);
			} else
				qa_mailfollow_ajax_error();
		}
	);
	
	qa_mailfollow_show_waiting_after(elem, false);
	
	return false;
}

function qa_mailfollow_ajax_post(operation, params, callback)
{
	jQuery.extend(params, {qa:'ajax', qa_operation:operation, qa_root:qa_root, qa_request:qa_request});

	jQuery.post('../qa-plugin/mail-follow/mail-follow-ajax-process.php', params, function(response) {
		var header='QA_AJAX_RESPONSE';
		var headerpos=response.indexOf(header);
		
		if (headerpos>=0)
			callback(response.substr(headerpos+header.length).replace(/^\s+/, '').split("\n"));
		else
			callback([]);

	}, 'text').error(function(jqXHR) { if (jqXHR.readyState>0) callback([]) });
}

function qa_mailfollow_ajax_error()
{
	alert('Unexpected response from server - please try again or switch off Javascript.');
}