```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Railway Reservation System</title>
    <style>
        * { box-sizing: border-box; }
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f3f6fb;
            color: #1e293b;
        }
        header {
            background: #123b70;
            color: white;
            padding: 24px 8%;
        }
        header h1 { margin: 0 0 8px; }
        nav { margin-top: 18px; }
        nav a {
            color: white;
            margin-right: 22px;
            text-decoration: none;
        }
        main { max-width: 1000px; margin: 35px auto; padding: 0 20px; }
        .hero, .card {
            background: white;
            padding: 28px;
            border-radius: 12px;
            box-shadow: 0 4px 16px #0000000d;
            margin-bottom: 24px;
        }
        .hero { border-top: 5px solid #f59e0b; }
        form { display: grid; gap: 15px; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); }
        label { display: block; font-weight: bold; margin-bottom: 7px; }
        input, button {
            width: 100%;
            padding: 12px;
            border-radius: 6px;
            font-size: 15px;
        }
        input { border: 1px solid #cbd5e1; }
        button {
            background: #123b70;
            color: white;
            border: 0;
            cursor: pointer;
        }
        button:hover { background: #1d4f91; }
        .cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 20px; }
        footer { text-align: center; padding: 22px; color: #64748b; }
        #message { margin-top: 16px; font-weight: bold; }
    </style>
</head>
<body>
    <header>
        <h1>🚆 Railway Reservation System</h1>
        <p>Plan your journey. Search trains. Travel with confidence.</p>
        <nav>
            <a href="#search">Search Trains</a>
            <a href="#services">Services</a>
        </nav>
    </header>

    <main>
        <section class="hero" id="search">
            <h2>Find Your Train</h2>
            <p>Enter your journey details to get started.</p>
            <form id="searchForm">
                <div>
                    <label for="from">From</label>
                    <input id="from" required placeholder="Departure station">
                </div>
                <div>
                    <label for="to">To</label>
                    <input id="to" required placeholder="Destination station">
                </div>
                <div>
                    <label for="date">Journey Date</label>
                    <input id="date" type="date" required>
                </div>
                <div style="align-self:end">
                    <button type="submit">Search Trains</button>
                </div>
            </form>
            <p id="message"></p>
        </section>

        <section id="services">
            <h2>Our Services</h2>
            <div class="cards">
                <article class="card">
                    <h3>🎫 Book Tickets</h3>
                    <p>Start planning your next railway journey.</p>
                </article>
                <article class="card">
                    <h3>📋 My Reservations</h3>
                    <p>Find your booking details and reservation status.</p>
                </article>
                <article class="card">
                    <h3>❌ Cancel Ticket</h3>
                    <p>Manage a reservation when your plans change.</p>
                </article>
            </div>
        </section>
    </main>

    <footer>Railway Reservation System | Student Project</footer>

    <script>
        document.getElementById("searchForm").addEventListener("submit", function(event) {
            event.preventDefault();
            const from = document.getElementById("from").value.trim();
            const to = document.getElementById("to").value.trim();

            if (from.toLowerCase() === to.toLowerCase()) {
                document.getElementById("message").textContent =
                    "Please select different departure and destination stations.";
                return;
            }

            document.getElementById("message").textContent =
                "Search form working! Train results will appear after backend integration.";
        });
    </script>
</body>
</html>
```