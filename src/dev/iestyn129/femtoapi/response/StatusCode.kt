package dev.iestyn129.femtoapi.response

enum class StatusCode(val code: Int) {
	Continue(100),
	SwitchingProtocols(101),
	Processing(102),
	EarlyHints(103),
	OK(200),
	Created(201),
	Accepted(202),
	NonAuthoritativeInformation(203),
	NoContent(204),
	ResetContent(205),
	PartialContent(206),
	MultiStatus(207),
	AlreadyReported(208),
	ThisIsFine(218),
	ImUsed(226),
	MultipleChoices(300),
	MovedPermanently(301),
	Found(302),
	SeeOther(303),
	NotModified(304),
	SwitchProxy(306),
	TemporaryRedirect(307),
	PermanentRedirect(308),
	BadRequest(400),
	Unauthorized(401),
	PaymentRequired(402),
	Forbidden(403),
	NotFound(404),
	MethodNotAllowed(405),
	NotAcceptable(406),
	ProxyAuthenticationRequired(407),
	RequestTimeout(408),
	Conflict(409),
	Gone(410),
	LengthRequired(411),
	PreconditionFailed(412),
	RequestEntityTooLarge(413),
	RequestURITooLong(414),
	UnsupportedMediaType(415),
	RequestedRangeNotSatisfiable(416),
	ExpectationFailed(417),
	ImATeapot(418),
	PageExpired(419),
	MethodFailure(420),
	EnhanceYourCalm(420),
	MisdirectedRequest(421),
	UnprocessableEntity(422),
	UnprocessableContent(422),
	Locked(423),
	FailedDependency(424),
	TooEarly(425),
	UpgradeRequired(426),
	PreconditionRequired(428),
	TooManyRequests(429),
	RequestHeaderFieldsTooLargeAlt(430),
	RequestHeaderFieldsTooLarge(431),
	RequestHeaderFieldsTooLargeAlt2(432),
	LoginTimeout(440),
	ConnectionClosedWithoutResponse(444),
	RetryWith(449),
	BlockedByWindowsParentalControls(450),
	UnavailableForLegalReasons(451),
	ClientClosedConnection(460),
	RequestHeaderTooLarge(494),
	SSLCertificateError(495),
	SSLCertificateRequired(496),
	HTTPRequestSentToHTTPSPort(497),
	InvalidToken(498),
	ClientClosedRequest(499),
	InternalServerError(500),
	NotImplemented(501),
	BadGateway(502),
	ServiceUnavailable(503),
	GatewayTimeout(504),
	HTTPVersionNotSupported(505),
	VariantAlsoNegotiates(506),
	InsufficientStorage(507),
	LoopDetected(508),
	BandwidthLimitExceeded(509),
	NotExtended(510),
	NetworkAuthenticationRequired(511),
	UnknownError(520),
	WebServerIsDown(521),
	ConnectionTimedOut(522),
	OriginIsUnreachable(523),
	ATimeoutOccurred(524),
	SSLHandshakeFailed(525),
	InvalidSSLCertificate(526),
	RailgunListenerToOriginError(527),
	SiteIsOverloaded(529),
	OriginDNSError(530),
	UnauthorizedCloudFront(561),
	NetworkReadTimeoutError(598),
	NetworkConnectTimeoutError(599);

	companion object {
		fun fromCode(code: Int): StatusCode? = entries.find { code == it.code }
	}

	override fun toString(): String = when(this) {
		Continue -> "Continue"
		SwitchingProtocols -> "Switching Protocols"
		Processing -> "Processing"
		EarlyHints -> "Early Hints"
		OK -> "OK"
		Created -> "Created"
		Accepted -> "Accepted"
		NonAuthoritativeInformation -> "Non-Authoritative Information"
		NoContent -> "No Content"
		ResetContent -> "Reset Content"
		PartialContent -> "Partial Content"
		MultiStatus -> "Multi-Status"
		AlreadyReported -> "Already Reported"
		ThisIsFine -> "This is fine (Apache Web Server)"
		ImUsed -> "IM Used"
		MultipleChoices -> "Multiple Choices"
		MovedPermanently -> "Moved Permanently"
		Found -> "Found"
		SeeOther -> "See Other"
		NotModified -> "Not Modified"
		SwitchProxy -> "Switch Proxy"
		TemporaryRedirect -> "Temporary Redirect"
		PermanentRedirect -> "Permanent Redirect"
		BadRequest -> "Bad Request"
		Unauthorized -> "Unauthorized"
		PaymentRequired -> "Payment Required"
		Forbidden -> "Forbidden"
		NotFound -> "Not Found"
		MethodNotAllowed -> "Method Not Allowed"
		NotAcceptable -> "Not Acceptable"
		ProxyAuthenticationRequired -> "Proxy Authentication Required"
		RequestTimeout -> "Request Timeout"
		Conflict -> "Conflict"
		Gone -> "Gone"
		LengthRequired -> "Length Required"
		PreconditionFailed -> "Precondition Failed"
		RequestEntityTooLarge -> "Request Entity Too Large"
		RequestURITooLong -> "Request-URI Too Long"
		UnsupportedMediaType -> "Unsupported Media Type"
		RequestedRangeNotSatisfiable -> "Requested Range Not Satisfiable"
		ExpectationFailed -> "Expectation Failed"
		ImATeapot -> "I'm a teapot"
		PageExpired -> "Page Expired (Laravel Framework)"
		MethodFailure -> "Method Failure (Spring Framework)"
		EnhanceYourCalm -> "Enhance Your Calm"
		MisdirectedRequest -> "Misdirected Request"
		UnprocessableEntity -> "Unprocessable Entity"
		UnprocessableContent -> "Unprocessable Content"
		Locked -> "Locked"
		TooEarly -> "Too Early"
		FailedDependency -> "Failed Dependency"
		UpgradeRequired -> "Upgrade Required"
		PreconditionRequired -> "Precondition Required"
		TooManyRequests -> "Too Many Requests"
		RequestHeaderFieldsTooLargeAlt -> "Request Header Fields Too Large"
		RequestHeaderFieldsTooLarge -> "Request Header Fields Too Large"
		RequestHeaderFieldsTooLargeAlt2 -> "Request Header Fields Too Large"
		LoginTimeout -> "Login Time-out"
		ConnectionClosedWithoutResponse -> "Connection Closed Without Response"
		RetryWith -> "Retry With"
		BlockedByWindowsParentalControls -> "Blocked by Windows Parental Controls"
		UnavailableForLegalReasons -> "Unavailable For Legal Reasons"
		ClientClosedConnection -> "Client Closed Connection"
		RequestHeaderTooLarge -> "Request Header Too Large"
		SSLCertificateError -> "SSL Certificate Error"
		SSLCertificateRequired -> "SSL Certificate Required"
		HTTPRequestSentToHTTPSPort -> "HTTP Request Sent to HTTPS Port"
		InvalidToken -> "Invalid Token (Esri)"
		ClientClosedRequest -> "Client Closed Request"
		InternalServerError -> "Internal Server Error"
		NotImplemented -> "Not Implemented"
		BadGateway -> "Bad Gateway"
		ServiceUnavailable -> "Service Unavailable"
		GatewayTimeout -> "Gateway Timeout"
		HTTPVersionNotSupported -> "HTTP Version Not Supported"
		VariantAlsoNegotiates -> "Variant Also Negotiates"
		InsufficientStorage -> "Insufficient Storage"
		LoopDetected -> "Loop Detected"
		BandwidthLimitExceeded -> "Bandwidth Limit Exceeded"
		NotExtended -> "Not Extended"
		NetworkAuthenticationRequired -> "Network Authentication Required"
		UnknownError -> "Unknown Error"
		WebServerIsDown -> "Web Server Is Down"
		ConnectionTimedOut -> "Connection Timed Out"
		OriginIsUnreachable -> "Origin Is Unreachable"
		ATimeoutOccurred -> "A Timeout Occurred"
		SSLHandshakeFailed -> "SSL Handshake Failed"
		InvalidSSLCertificate -> "Invalid SSL Certificate"
		SiteIsOverloaded -> "Site Is Overloaded"
		RailgunListenerToOriginError -> "Railgun Listener to Origin Error"
		OriginDNSError -> "Origin DNS Error"
		UnauthorizedCloudFront -> "Unauthorized (CloudFront)"
		NetworkReadTimeoutError -> "Network Read Timeout Error"
		NetworkConnectTimeoutError -> "Network Connect Timeout Error"
	}
}