<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	table {border-collapse: collapse;}
	table td, table th {padding: 5px;}
</style>

<h3 style="margin-top: 10px; text-align: center;">Demo query</h3>

<h4>participantNames:</h4>
<ul>
	<c:forEach items="${participantNames}" var="name">
		<li>${name}</li>
	</c:forEach>
</ul>

<h4>usernameAndFullnames:</h4>
<ul>
	<c:forEach items="${usernameAndFullnames}" var="uaf">
		<li>${uaf[0]} - ${uaf[1]}</li>
	</c:forEach>
</ul>

<h4>newestUsernameAndFullnames:</h4>
<ul>
	<c:forEach items="${newestUsernameAndFullnames}" var="uaf">
		<li>${uaf[0]} - ${uaf[1]}</li>
	</c:forEach>
</ul>

<h4>commentsOfUser:</h4>
<table border="1">
	<tr>
		<th>Fullname</th>
		<th>Content</th>
		<th>Time</th>
		<th>Room's name</th>
	</tr>
	<c:forEach items="${commentsOfUser}" var="cou">
		<tr>
			<td>${cou[0]}</td>
			<td>${cou[1]}</td>
			<td>${cou[2]}</td>
			<td>${cou[3]}</td>
		</tr>
	</c:forEach>
</table>

<h4>usernameAndFullnamesJPQL:</h4>
<ul>
	<li>${usernameAndFullnamesJPQL.username} - ${usernameAndFullnamesJPQL.fullname}
	(Các field không được select sẽ không có giá trị(kiểu Wrapper) hoặc có giá trị 
	mặc định (kiểu nguyên thủy): 
	${usernameAndFullnamesJPQL.id}, 
	${usernameAndFullnamesJPQL.password}...)</li>
</ul>
<path></path>
<style>
	path {
		d: path('M 254 147.1 c 0 -12.7 -4.4 -16.4 -9 -20.1 c 2.6 -4.2 5.1 -10.2 5.1 -18 c 0 -15.8 -12.3 -25.7 -32 -25.7 h -52 c -0.5 0 -1 -0.5 -0.9 -1 c 1.4 -8.6 3 -24 3 -31.7 c 0 -16.7 -4 -37.5 -19.3 -45.7 c -4.5 -2.4 -8.3 -3.7 -14.1 -3.7 c -8.8 0 -14.6 3.6 -16.7 5.9 c -1.3 1.4 -1.9 3.3 -1.8 5.2 l 1.3 34.6 c 0.2 2.8 -0.3 5.4 -1.6 7.7 l -24 47.8 c -1.7 3.5 -4.2 6.6 -7.6 8.5 c -3.5 2 -6.5 5.9 -6.5 9.5 v 94.8 C 78 230 94 238 112.3 238 h 86.1 c 13.5 0 22.4 -4.5 27.2 -13.5 c 4.4 -8.2 3.2 -15.8 1.4 -21.5 c 7.4 -2.3 14.8 -8 16.9 -18.3 c 1.3 -6.6 -0.7 -12.1 -2.9 -16.2 C 247.5 165 254 159.8 254 147.1 Z');
		fill: rgb(19, 207, 19);
		stroke: transparent;
		stroke-linecap: round;
		stroke-width: 5%;
	}
</style>
<script>
	// self.addEventListener('push', function(e) {
	// 	var options = {
	// 		body: 'This notification was generated from a push!',
	// 		icon: 'images/example.png',
	// 		vibrate: [100, 50, 100],
	// 		data: {
	// 			dateOfArrival: Date.now(),
	// 			primaryKey: '2'
	// 		},
	// 		actions: [
	// 			{action: 'explore', title: 'Explore this new world',
	// 				icon: 'images/checkmark.png'},
	// 			{action: 'close', title: 'Close',
	// 				icon: 'images/xmark.png'},
	// 		]
	// 	};
		
	// 	// This extends the lifetime of the push event until the
	// 	// showNotification promise resolves. In general, we use the waitUntil
	// 	// method to ensure the service worker doesn't terminate before
	// 	// an asynchronous operation has completed
	// 	e.waitUntil(
	// 		self.registration.showNotification('Hello world!', options)
	// 	);
	// });

	// if ('serviceWorker' in navigator) {
	// 	navigator.serviceWorker.register('sw.js').then(function (reg) {
	// 		console.log('Service Worker Registered!', reg);

	// 		reg.pushManager.getSubscription().then(function (sub) {
	// 			if (sub === null) {
	// 				// Update UI to ask user to register for Push
	// 				console.log('Not subscribed to push service!');
	// 			} else {
	// 				// We have a subscription, update the database
	// 				console.log('Subscription object: ', sub);
	// 			}
	// 		});
	// 	}).catch(function (err) {
	// 			console.log('Service Worker registration failed: ', err);
	// 		});
	// }

	function subscribeUser() {
		if ('serviceWorker' in navigator) {
			navigator.serviceWorker.ready.then(function (reg) {

				reg.pushManager.subscribe({
					userVisibleOnly: true
				}).then(function (sub) {
					console.log('Endpoint URL: ', sub.endpoint);
				}).catch(function (e) {
					if (Notification.permission === 'denied') {
						console.warn('Permission for notifications was denied');
					} else {
						console.error('Unable to subscribe to push', e);
					}
				});
			}).catch(function(err) {
				console.log("Error: ", err);
			})
		} else {
			console.log("'serviceWorker' is NOT in navigator");
		}
	}

	subscribeUser();
</script>