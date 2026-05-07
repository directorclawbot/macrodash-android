# MacroDash — Product Requirements Document

**Version:** 1.0
**Date:** 2026-05-07
**Status:** Draft

---

## 1. Overview & Vision

### What is MacroDash?

MacroDash is a native Android dashboard application that surfaces real-time macroeconomic indicators from FRED (Federal Reserve Economic Data) and Yahoo Finance, organizing them by investment risk time-horizon (Short / Mid / Long Term). It gives individual investors, traders, and macro enthusiasts a single, scannable view of the economic landscape — with optional AI-powered synthesis via Ollama (local) or OpenAI.

### The Vision

Stop jumping between FRED's website, Yahoo Finance, Bloomberg, and ChatGPT just to answer one question: *"What does the macro picture look like right now?"*

MacroDash collapses that workflow into one glanceable screen. The time-horizon toggle lets users filter to the horizon they care about — a day-trader filtering to short-term macro signals, a retirement saver watching long-term inflation trends. The AI Insights panel then synthesizes the current dashboard state into plain-English commentary, flagging which indicators are flashing risk.

### Target Users

- Retail investors managing their own portfolio exposure
- Traders monitoring macro tailwinds and headwinds
- Finance students tracking economic data in real time
- Financial advisors needing a quick economic briefing for client calls

### Design Principles

1. **Signal over noise** — Every pixel serves the mission of making macro state scannable in under 10 seconds.
2. **Offline-first** — Cached data is always visible; network is enhancement, not requirement.
3. **Zero configuration to value** — App shows real data immediately after the user enters a FRED API key.
4. **AI as an optional amplifier** — The dashboard works with zero AI. AI is turned on, not forced in.

---

## 2. User Stories

### US-1: First-Launch Setup
> As a new user, I open MacroDash for the first time and immediately see a setup prompt asking for my FRED API key, so I can start seeing real economic data within 60 seconds of downloading the app.

**Acceptance:** App launches to an onboarding/setup screen. User enters FRED key and taps "Get Started." Dashboard populates with all indicators.

### US-2: Glance at All Macro Indicators
> As a user, I open the app and instantly see the current value, % change, and last-updated date for all indicators across every category, so I can assess the macro environment without clicking anything.

**Acceptance:** Single-scroll dashboard shows all indicator cards. Each card shows value, delta badge, and timestamp.

### US-3: Filter by Time Horizon
> As a user, I toggle "Short Term" off so I can focus on long-term retirement indicators, and the dashboard immediately filters to show only Mid and Long Term indicators.

**Acceptance:** Three toggle chips at the top of the dashboard. Deselecting a horizon hides all indicators tagged with that horizon. Selecting all three shows all indicators.

### US-4: Pull to Refresh
> As a user, I pull down on the dashboard to force a data refresh when I suspect the cached data is stale, and the app fetches fresh values and updates all cards simultaneously.

**Acceptance:** Pull-to-refresh gesture triggers all data sources. Loading state shown during fetch. All cards update atomically.

### US-5: View AI Insights
> As a user, I tap the "AI Insights" tab, the app queries my local Ollama instance and displays a plain-English summary of the current macro state, flagging risk signals.

**Acceptance:** AI Insights panel shows a loading spinner, then a readable summary card. Error state shown if Ollama is unreachable or not configured.

### US-6: Configure Ollama
> As a user, I go to Settings and configure my local Ollama endpoint (defaulting to `http://localhost:11434`) and select which model to use for AI analysis.

**Acceptance:** Settings screen has a Ollama URL field (pre-filled), model selector dropdown, and a "Test Connection" button that shows a success/failure toast.

### US-7: Configure OpenAI as Alternative AI Provider
> As a user, I prefer cloud AI. I enter my OpenAI API key in Settings and select `gpt-4o-mini` as my model. The AI Insights panel routes to OpenAI instead of Ollama.

**Acceptance:** OpenAI key field and model selector appear when user selects OpenAI as the provider. Ollama fields are hidden or disabled.

### US-8: Offline Viewing
> As a user with no network connectivity, I open the app and see all last-cached indicators with a subtle "Offline — showing cached data from [timestamp]" banner.

**Acceptance:** App opens fully functional with cached data. Banner visible. All indicator values readable. No crashes.

### US-9: Change Refresh Interval
> As a user, I go to Settings and change the auto-refresh interval from 15 minutes to 60 minutes to conserve battery and data.

**Acceptance:** Refresh interval picker (5 min / 15 min / 30 min / 60 min / Manual only). Setting persists across app restarts.

### US-10: Error State — No FRED Key
> As a user who hasn't configured a FRED API key, I see a prominent prompt in the dashboard area asking me to add one in Settings, rather than a blank or broken screen.

**Acceptance:** Empty state with icon, message "Add your FRED API key to get started," and a "Go to Settings" button.

---

## 3. Feature Requirements

### 3.1 Indicator Dashboard

**FR-DASH-1:** The dashboard MUST display a scrollable list of indicator cards organized by category. Each card displays:
- Indicator name (e.g., "Fed Funds Rate")
- Current value (e.g., "5.25%")
- Percentage change vs. prior period (e.g., "+0.00%", "-0.12%") with color coding: green for positive, red for negative, gray for unchanged
- Last updated date/time (e.g., "Updated May 7, 2026")

**FR-DASH-2:** Categories displayed in fixed section headers:
- Interest Rates
- Inflation
- Employment
- GDP & Growth
- Consumer & Housing
- Money Supply
- Trade & Dollar
- Stock Markets

**FR-DASH-3:** Each section is collapsible/expandable. Default state: all expanded.

**FR-DASH-4:** Pull-to-refresh triggers a full data refresh across all active data sources.

**FR-DASH-5:** A floating action button (or toolbar button) labeled "Refresh" triggers the same behavior as pull-to-refresh.

**FR-DASH-6:** A persistent status bar at the top of the dashboard shows:
- "Last refreshed: [timestamp]" or "Refreshing..."
- A small dot indicator: green = fresh (< 15 min old), yellow = stale (15–60 min), red = very stale (> 60 min)

### 3.2 Risk Time-Horizon Toggle

**FR-HORIZ-1:** Three toggle chips displayed horizontally at the top of the dashboard: "Short Term", "Mid Term", "Long Term". All three selected by default.

**FR-HORIZ-2:** Each indicator in the data layer is tagged with one or more risk horizons:
- **Short Term** (< 6 months relevance): e.g., Fed Funds Rate, 30Y Mortgage, VIX, Retail Sales, Housing Starts
- **Mid Term** (6 months – 2 years): e.g., 10Y Treasury, CPI, Unemployment Rate, Consumer Confidence, M1/M2
- **Long Term** (> 2 years): e.g., Real GDP, GDP Growth Rate, ISM Manufacturing PMI, Trade Balance, Current Account, DXY

**FR-HORIZ-3:** Deselecting a chip immediately filters the visible indicators. Selecting a chip restores them. Animations should be smooth (300ms fade/slide).

**FR-HORIZ-4:** If all three chips are deselected, treat as "All" (show everything). The UI should prevent this state (at least one must remain selected) or handle it gracefully by showing all.

### 3.3 AI Insights Panel

**FR-AI-1:** AI Insights is accessible via a bottom navigation tab or a dedicated top-level tab labeled "AI Insights."

**FR-AI-2:** The panel shows a single "Analyze Now" button. Tapping it triggers the AI analysis flow.

**FR-AI-3:** While analyzing, show a skeleton loader or spinner with the text "Analyzing macro conditions..."

**FR-AI-4:** The analysis prompt fed to the LLM MUST include:
- Current timestamp
- All visible indicator values and % changes
- Which time horizons are currently active
- A specific instruction to flag risk signals (deviation > 1σ from recent mean, momentum direction, spread anomalies)

**FR-AI-5:** The result is displayed as a single readable card with:
- A title: "Macro Risk Summary — [date]"
- 3–6 bullet points or short paragraphs summarizing key signals
- A badge indicating which AI provider was used (Ollama / OpenAI)

**FR-AI-6:** The panel caches the last analysis result. If the user returns to AI Insights without triggering a new analysis, show the cached result with a "Refreshed [time]" label and an "Update" button.

**FR-AI-7:** Model selector within AI Insights (or Settings) allows choosing:
- Ollama models available on the configured endpoint (probed via `GET /api/tags`)
- OpenAI models: `gpt-4o`, `gpt-4o-mini`, `gpt-4-turbo`

### 3.4 Data Refresh & Caching

**FR-CACHE-1:** All indicator data MUST be stored in a local Room database upon first successful fetch.

**FR-CACHE-2:** On app launch, the UI immediately displays cached data (if any) before attempting a network fetch.

**FR-CACHE-3:** Background refresh runs every N minutes (configurable, default 15) using WorkManager.

**FR-CACHE-4:** If a refresh fails, the app continues displaying cached data with an error toast: "Refresh failed — check your connection."

**FR-CACHE-5:** The Room database schema MUST include: `indicator_id`, `value`, `percent_change`, `last_updated`, `fetched_at`.

**FR-CACHE-6:** Network errors MUST NOT cause a crash. All network calls wrapped in try/catch with user-facing error states.

### 3.5 Settings

**FR-SETTINGS-1:** Settings screen accessible from a toolbar icon or bottom nav. Contains:

| Field | Type | Default |
|---|---|---|
| FRED API Key | Text input (masked) | Empty |
| AI Provider | Radio: Ollama / OpenAI | Ollama |
| Ollama Endpoint | Text input | `http://localhost:11434` |
| Ollama Model | Dropdown (probed from endpoint) | First available |
| OpenAI API Key | Text input (masked) | Empty |
| OpenAI Model | Dropdown | `gpt-4o-mini` |
| Refresh Interval | Picker: 5/15/30/60 min / Manual | 15 min |
| Default Horizons | Multi-select chips | All selected |

**FR-SETTINGS-2:** "Test FRED Connection" button probes the FRED API with the provided key and shows success/failure.

**FR-SETTINGS-3:** "Test Ollama Connection" button pings the configured Ollama endpoint and lists available models.

**FR-SETTINGS-4:** All settings persist using Jetpack DataStore (Preferences). No SharedPreferences.

**FR-SETTINGS-5:** Settings changes take effect immediately (no "Save" button needed — auto-save on change).

---

## 4. Technical Architecture

### 4.1 Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                      UI Layer (Compose)                       │
│   Screens: Dashboard │ AI Insights │ Settings              │
│   ViewModels: DashboardVM │ AIInsightsVM │ SettingsVM      │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                   Domain Layer (Use Cases)                   │
│   GetIndicatorsUseCase │ RefreshIndicatorsUseCase           │
│   GetAIAnalysisUseCase  │ ProbeOllamaModelsUseCase          │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                    Data Layer (Repositories)                 │
│   FREDRepository │ YahooFinanceRepository │ AIRepository    │
│   SettingsRepository (DataStore)                            │
└──────┬──────────────────┬──────────────────┬────────────────┘
       │                  │                  │
┌──────▼──────┐   ┌───────▼───────┐   ┌──────▼──────┐
│  FRED API  │   │ Yahoo Finance  │   │  Ollama /   │
│  (Retrofit)│   │  (yfinance)   │   │  OpenAI API │
└────────────┘   └───────────────┘   └─────────────┘
       │                                   │
       └──────────────┬────────────────────┘
                      │
              ┌───────▼───────┐
              │ Room Database │
              │   (Indicators)│
              └───────────────┘
```

### 4.2 Module Structure

```
macro-dash/
├── app/
│   ├── src/main/
│   │   ├── java/com/macrodash/app/
│   │   │   ├── MacroDashApplication.kt       # Hilt Application
│   │   │   ├── MainActivity.kt
│   │   │   ├── di/                            # Hilt modules
│   │   │   │   ├── AppModule.kt
│   │   │   │   ├── NetworkModule.kt
│   │   │   │   └── DatabaseModule.kt
│   │   │   ├── data/
│   │   │   │   ├── local/
│   │   │   │   │   ├── MacroDashDatabase.kt  # Room DB
│   │   │   │   │   ├── dao/
│   │   │   │   │   └── entity/
│   │   │   │   ├── remote/
│   │   │   │   │   ├── fred/
│   │   │   │   │   │   ├── FredApiService.kt
│   │   │   │   │   │   └── FredDto.kt
│   │   │   │   │   ├── yahoo/
│   │   │   │   │   │   └── YahooDataSource.kt
│   │   │   │   │   └── ai/
│   │   │   │   │       ├── OllamaApiService.kt
│   │   │   │   │       └── OpenAIApiService.kt
│   │   │   │   └── repository/
│   │   │   │       ├── FredRepositoryImpl.kt
│   │   │   │       ├── YahooRepositoryImpl.kt
│   │   │   │       ├── AIRepositoryImpl.kt
│   │   │   │       └── SettingsRepository.kt
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Indicator.kt
│   │   │   │   │   ├── IndicatorCategory.kt
│   │   │   │   │   ├── RiskHorizon.kt
│   │   │   │   │   └── AIAnalysisResult.kt
│   │   │   │   ├── repository/
│   │   │   │   │   ├── IndicatorRepository.kt  # Interface
│   │   │   │   │   └── AIRepository.kt         # Interface
│   │   │   │   └── usecase/
│   │   │   │       ├── GetIndicatorsUseCase.kt
│   │   │   │       ├── RefreshIndicatorsUseCase.kt
│   │   │   │       ├── GetAIAnalysisUseCase.kt
│   │   │   │       └── GetOllamaModelsUseCase.kt
│   │   │   ├── ui/
│   │   │   │   ├── theme/
│   │   │   │   │   ├── Theme.kt
│   │   │   │   │   ├── Color.kt
│   │   │   │   │   └── Type.kt
│   │   │   │   ├── navigation/
│   │   │   │   │   └── NavGraph.kt
│   │   │   │   ├── dashboard/
│   │   │   │   │   ├── DashboardScreen.kt
│   │   │   │   │   ├── DashboardViewModel.kt
│   │   │   │   │   └── components/
│   │   │   │   │       ├── IndicatorCard.kt
│   │   │   │   │       ├── HorizonToggleChips.kt
│   │   │   │   │       └── RefreshStatusBar.kt
│   │   │   │   ├── aiinsights/
│   │   │   │   │   ├── AIInsightsScreen.kt
│   │   │   │   │   ├── AIInsightsViewModel.kt
│   │   │   │   │   └── components/
│   │   │   │   │       └── AnalysisCard.kt
│   │   │   │   └── settings/
│   │   │   │       ├── SettingsScreen.kt
│   │   │   │       └── SettingsViewModel.kt
│   │   │   └── util/
│   │   │       ├── NetworkMonitor.kt
│   │   │       └── Resource.kt               # Result wrapper
│   │   └── res/
│   └── build.gradle.kts
└── gradle/
```

### 4.3 Architecture Rules

- **Unidirectional data flow:** UI State flows down; events flow up.
- **Repository pattern:** All data access goes through repository interfaces in `domain/`, implementations in `data/`.
- **Use Cases:** Business logic (filtering by horizon, building the AI prompt) lives in Use Cases, not ViewModels.
- **Dependency Inversion:** Domain layer has no dependencies on data layer classes.
- **Single Activity:** One `MainActivity` hosts all Compose screens via Navigation.

### 4.4 Key Libraries

| Purpose | Library | Version |
|---|---|---|
| UI | Jetpack Compose BOM | 2024.02.00 |
| Design System | Material 3 | (via Compose BOM) |
| Navigation | Navigation Compose | 2.7.7 |
| DI | Hilt | 2.50 |
| Networking | Retrofit | 2.9.0 |
| Networking | OkHttp | 4.12.0 |
| JSON | Kotlinx Serialization | 1.6.2 |
| Database | Room | 2.6.1 |
| Preferences | DataStore Preferences | 1.0.0 |
| Background Work | WorkManager | 2.9.0 |
| Async | Kotlin Coroutines | 1.7.3 |
| Yahoo Finance | yfinance | 0.2.37 (via Android Python bridge — see Note 1) |

> **Note 1:** yfinance is a Python library. On Android, this requires either:
> - A Kotlin/JVM Yahoo Finance wrapper (e.g., a lightweight REST call to a unofficial Yahoo Finance mobile API)
> - Running yfinance in a Python interpreter via Chaquopy (paid plugin) or BeeWare
> - **Recommended for v1:** Use direct Yahoo Finance REST API calls via Retrofit (unofficial but stable `/v7/finance/quote` endpoint) rather than yfinance Python library.

---

## 5. Data Model

### 5.1 Domain Entities

```kotlin
enum class RiskHorizon { SHORT, MID, LONG }

enum class IndicatorCategory {
    INTEREST_RATES,
    INFLATION,
    EMPLOYMENT,
    GDP_GROWTH,
    CONSUMER_HOUSING,
    MONEY_SUPPLY,
    TRADE_DOLLAR,
    STOCK_MARKETS
}

data class Indicator(
    val id: String,                    // FRED series ID or Yahoo ticker
    val name: String,                   // Display name
    val value: String,                  // Formatted value string
    val rawValue: Double,               // Raw numeric for calculations
    val percentChange: Double,          // % change from prior period
    val category: IndicatorCategory,
    val horizons: Set<RiskHorizon>,     // One or more horizons
    val lastUpdated: Instant,           // When FRED/Yahoo last updated
    val fetchedAt: Instant,             // When we fetched it
    val source: DataSource              // FRED or YAHOO
)

enum class DataSource { FRED, YAHOO }
```

### 5.2 Room Entities

```kotlin
@Entity(tableName = "indicators")
data class IndicatorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val value: String,
    val rawValue: Double,
    val percentChange: Double,
    val category: String,          // Stored as String enum
    val horizons: String,           // Comma-separated: "SHORT,MID"
    val lastUpdated: Long,          // Epoch millis
    val fetchedAt: Long,           // Epoch millis
    val source: String             // "FRED" or "YAHOO"
)

@Entity(tableName = "ai_analysis_cache")
data class AIAnalysisCacheEntity(
    @PrimaryKey val id: Int = 1,   // Singleton
    val content: String,
    val provider: String,          // "OLLAMA" or "OPENAI"
    val model: String,
    val generatedAt: Long
)
```

### 5.3 Indicator Catalog (FRED Series IDs + Yahoo Tickers)

| Category | Indicator Name | Source | FRED Series ID / Yahoo Ticker | Horizon |
|---|---|---|---|---|
| Interest Rates | Federal Funds Rate | FRED | FEDFUNDS | SHORT |
| Interest Rates | 10-Year Treasury Yield | FRED | DGS10 | SHORT |
| Interest Rates | 30-Year Fixed Mortgage | FRED | MORTGAGE30US | SHORT |
| Inflation | Consumer Price Index (YoY) | FRED | CPIAUCSL | MID |
| Inflation | Producer Price Index (YoY) | FRED | PPIFIS | MID |
| Inflation | PCE Deflator (YoY) | FRED | PCEPI | MID |
| Employment | Unemployment Rate | FRED | UNRATE | MID |
| Employment | Nonfarm Payrolls | FRED | PAYEMS | MID |
| Employment | Labor Force Participation | FRED | CIVPART | LONG |
| GDP & Growth | Real GDP (Q/Q) | FRED | GDPC1 | LONG |
| GDP & Growth | GDP Growth Rate (Annual) | FRED | A191RP0Q | LONG |
| GDP & Growth | ISM Manufacturing PMI | FRED | ISM-MANU | MID |
| Consumer & Housing | Consumer Confidence Index | FRED | CONCCONUSD | MID |
| Consumer & Housing | Housing Starts | FRED | HOUST | SHORT |
| Consumer & Housing | Retail Sales (MoM) | FRED | RSXFS | SHORT |
| Money Supply | M1 Money Supply | FRED | M1SL | MID |
| Money Supply | M2 Money Supply | FRED | M2SL | MID |
| Money Supply | Monetary Base | FRED | BOGMBASE | LONG |
| Trade & Dollar | DXY Dollar Index | Yahoo | DX-Y.NYB | SHORT |
| Trade & Dollar | Trade Balance | FRED | BOPGSTB | LONG |
| Trade & Dollar | Current Account | FRED | BOPCTA | LONG |
| Stock Markets | S&P 500 | Yahoo | ^GSPC | SHORT |
| Stock Markets | NASDAQ Composite | Yahoo | ^IXIC | SHORT |
| Stock Markets | VIX | Yahoo | ^VIX | SHORT |

### 5.4 Refresh Cadence

| Source | Endpoint / Method | Refresh Cadence | Rate Limit |
|---|---|---|---|
| FRED | `fred/series/observations?series_id={id}` | 15 min (default) | 120 requests/min (free key) |
| Yahoo Finance | `/v7/finance/quote` (REST) | 15 min (default) | ~2000/day, 5/sec |
| Ollama | `GET /api/tags` | On-demand (Settings probe) | N/A |
| OpenAI | `POST /chat/completions` | On-demand (AI Insights trigger) | Pay-per-token |

**FRED Rate Limit Note:** With 22+ indicators, a full refresh = 22 FRED API calls. Free keys allow 120 calls/min. Using a 15-min refresh interval with all indicators, worst case is ~1.5 calls/min — well within limits. However, for efficiency, FRED offers `fred/series/observations` batch endpoints or a single call per series only. Consider caching aggressively.

---

## 6. AI Integration Design

### 6.1 Architecture

```
User taps "Analyze Now"
        │
        ▼
AIInsightsViewModel
        │
        ├─► If Ollama selected ──► OllamaApiService.POST /api/chat
        │                                       │
        │                          Request: {"model": "...", "messages": [...]}
        │                          Response: {"message": {"content": "..."}}
        │
        └─► If OpenAI selected ──► OpenAIApiService.POST /v1/chat/completions
                                          │
                              Request: {"model": "...", "messages": [...]}
                              Response: {"choices": [{"message": {"content": "..."}}]}
```

### 6.2 Prompt Engineering

**System Prompt:**
```
You are a macroeconomic analyst for an investment dashboard called MacroDash.
Given the current indicator values below, provide a concise risk summary.
For each indicator that appears to be flashing a risk signal — defined as:
  1. Deviation > 1 standard deviation from its trailing 12-month mean
  2. Strong momentum (|%change| > 2% in latest period)
  3. Spread anomalies (e.g., yield curve inversion, mortgage vs. treasury spread)
— call it out explicitly with a brief explanation.
Keep the summary to 4–6 bullet points. Be direct. No hedging.
```

**User Prompt Template:**
```
Current date: {date}
Active time horizons: {active_horizons}

Indicator snapshot:
{indicator_list_formatted}

Provide your risk summary now.
```

**Indicator List Format:**
```
[Category: Interest Rates]
- Fed Funds Rate: {value} ({change}%)
- 10Y Treasury: {value} ({change}%)
- 30Y Mortgage: {value} ({change}%)

[Category: Inflation]
- CPI (YoY): {value} ({change}%)
...
```

### 6.3 Ollama Configuration

- **Default endpoint:** `http://localhost:11434`
- **Chat endpoint:** `POST /api/chat`
- **Model list endpoint:** `GET /api/tags`
- **Request timeout:** 60 seconds
- **Recommended model for v1:** `llama3.2` (or `mistral` if llama3 unavailable)
- **Streaming:** Disabled for v1 (await full response)

### 6.4 OpenAI Configuration

- **Base URL:** `https://api.openai.com/v1`
- **Chat endpoint:** `POST /chat/completions`
- **Default model:** `gpt-4o-mini` (cost-effective, fast)
- **Request timeout:** 90 seconds
- **Max tokens:** 800

### 6.5 Caching

- AI analysis results are cached in `AIAnalysisCacheEntity`.
- Cache is invalidated when any indicator refreshes.
- Cache TTL: 1 hour (after which AI Insights shows "Stale — tap to refresh").

---

## 7. API Inventory

### 7.1 FRED API

**Base URL:** `https://api.stlouisfed.org/fred`

**Authentication:** Query param `?api_key={KEY}&file_type=json`

| Operation | Endpoint | Method | Params | Response |
|---|---|---|---|---|
| Get series observation (latest value) | `/fred/series/observations?series_id={id}` | GET | `series_id`, `api_key`, `file_type`, `observation_start=latest` | JSON array of observations |
| Get series metadata | `/fred/series?series_id={id}` | GET | `series_id`, `api_key`, `file_type` | JSON with series name/units |

**Observation Response Shape:**
```json
{
  "seriess": [
    {
      "series_id": "FEDFUNDS",
      "title": "Federal Funds Rate",
      "units": "Percent",
      "last_updated": "2026-05-07"
    }
  ],
  "observations": [
    {
      "date": "2026-05-01",
      "value": "5.25"
    }
  ]
}
```

**Key Series IDs:** See Section 5.3 Indicator Catalog.

### 7.2 Yahoo Finance API (REST)

**Base URL:** `https://query1.finance.yahoo.com`

**Endpoints Used:**

| Operation | Endpoint | Method | Params | Response |
|---|---|---|---|---|
| Get quote | `/v7/finance/quote` | GET | `symbols=^GSPC,^IXIC,^VIX,DX-Y.NYB` | JSON with quote data |
| Get basic financials | `/v10/finance/quoteSummary/{symbol}` | GET | `modules=summaryDetail,defaultKeyStatistics` | JSON financials |

**Quote Response Shape:**
```json
{
  "quoteResponse": {
    "result": [
      {
        "symbol": "^GSPC",
        "shortName": "S&P 500",
        "regularMarketPrice": 5187.42,
        "regularMarketChangePercent": 0.54,
        "regularMarketTime": 1715107200
      }
    ]
  }
}
```

**Note:** Yahoo Finance REST API does not require authentication for basic quotes. Respect `YHB` headers. Use with appropriate throttling.

### 7.3 Ollama API

**Base URL:** Configured by user (default `http://localhost:11434`)

| Operation | Endpoint | Method | Request | Response |
|---|---|---|---|---|
| List models | `/api/tags` | GET | — | `{"models": [{"name": "llama3.2:latest", ...}]}` |
| Chat completions | `/api/chat` | POST | `{"model": "...", "messages": [...]}` | `{"message": {"content": "..."}}` |

### 7.4 OpenAI API

**Base URL:** `https://api.openai.com/v1`

| Operation | Endpoint | Method | Request | Response |
|---|---|---|---|---|
| Chat completions | `/chat/completions` | POST | `{"model": "...", "messages": [...]}` | `{"choices": [{"message": {"content": "..."}}]}` |

---

## 8. Edge Cases & Error Handling

### 8.1 Network Errors

| Scenario | Handling |
|---|---|
| No internet on launch | Show cached data + orange "Offline" banner |
| Internet lost mid-refresh | Abort refresh, show error toast, retain cached data |
| FRED API returns 429 (rate limit) | Exponential backoff retry (1s, 2s, 4s, 8s), up to 3 attempts |
| FRED API returns 400 (bad series ID) | Log error, exclude that indicator from UI, show partial dashboard |
| FRED API returns 401/403 (invalid key) | Show setup prompt: "FRED API key invalid — check Settings" |
| Yahoo Finance API fails | Show partial dashboard (FRED indicators only), show indicator-level error badge on Yahoo items |
| Ollama unreachable | Show error card in AI Insights: "Ollama not reachable at [endpoint]. Check Settings." |
| OpenAI returns 401 | Show error card: "OpenAI API key invalid. Check Settings." |
| OpenAI returns 429 (rate limit) | Show error card: "OpenAI rate limit reached. Try again in a moment." |
| OpenAI returns 500 | Show error card: "OpenAI server error. Try again shortly." |

### 8.2 Data Quality

| Scenario | Handling |
|---|---|
| FRED returns `"."` for value (no data) | Display as "N/A", exclude from % change calculation |
| Yahoo returns null for price | Display as "N/A" |
| % change calculation results in NaN | Default to "0.00%" change display |
| Indicator last_updated is in the future (clock skew) | Ignore, treat as current |

### 8.3 App State

| Scenario | Handling |
|---|---|
| App killed during refresh | WorkManager handles retry. No partial state written to Room. |
| User changes FRED key mid-session | Invalidate FRED cache, trigger immediate refresh |
| User changes Ollama endpoint | Re-probe models, update model selector |
| All horizon chips deselected | Treat as "all selected" — at least one chip must be active (enforce in UI) |
| First launch, no cache, no network | Show empty state with setup prompt |
| Very old cache (> 24h) | Show red "Data may be outdated — [timestamp]" banner |

### 8.4 AI Analysis Edge Cases

| Scenario | Handling |
|---|---|
| Ollama model not found | Show error: "Model '{name}' not found. Check Settings." |
| Ollama response times out (> 60s) | Cancel request, show error card: "Analysis timed out. Try a smaller model." |
| AI response is empty or gibberish | Show error card: "Analysis failed. Try again." |
| Network available but Ollama not on LAN | Show error card with troubleshooting tip: "Is Ollama running on your network?" |

---

## 9. Milestones / Suggested Build Order

### Milestone 1: Shell + Foundation
**Goal:** App builds, navigates, displays a mock dashboard.

- [ ] Create Android project with Compose + Hilt scaffold
- [ ] Set up Room database with `IndicatorEntity` schema
- [ ] Implement DataStore for settings
- [ ] Set up Navigation (Dashboard / Settings tabs; AI Insights tab placeholder)
- [ ] Create UI theme with Material 3, dark mode support
- [ ] Build a static/mock dashboard with hardcoded indicator cards to validate UI
- [ ] Implement pull-to-refresh (mock loading state)
- [ ] Build Settings screen UI (all fields, no persistence yet)

**Duration:** ~2–3 days

---

### Milestone 2: FRED Data Layer
**Goal:** Real FRED data flows from API to dashboard to Room cache.

- [ ] Implement FredApiService with Retrofit
- [ ] Build FRED repository (single series fetch + batch handling)
- [ ] Implement `Indicator` domain model mapping from FRED DTOs
- [ ] Write FRED data to Room on fetch
- [ ] Read from Room cache on launch (immediate UI before network)
- [ ] Implement `% change` calculation (current vs. prior FRED observation)
- [ ] Build category section headers with collapse/expand
- [ ] Error state: invalid FRED key UX
- [ ] Error state: network failure UX

**Duration:** ~3–4 days

---

### Milestone 3: Yahoo Finance Data Layer
**Goal:** Yahoo Finance quotes appear on dashboard alongside FRED.

- [ ] Implement Yahoo Finance REST client (Retrofit)
- [ ] Build Yahoo repository
- [ ] Map Yahoo quote response to `Indicator` domain model
- [ ] Write Yahoo data to Room on fetch
- [ ] Handle Yahoo-specific error states (rate limits, null values)
- [ ] Deduplicate indicator list (interleave FRED + Yahoo by category)

**Duration:** ~2 days

---

### Milestone 4: Risk Horizon Filter
**Goal:** Horizon toggle chips filter the dashboard in real time.

- [ ] Tag all indicators with `RiskHorizon` in the data layer
- [ ] Build `HorizonToggleChips` composable (3 chips, multi-select)
- [ ] Implement filtering logic in `DashboardViewModel`
- [ ] Persist user's horizon selection in DataStore
- [ ] Enforce "at least one selected" constraint in UI

**Duration:** ~1–2 days

---

### Milestone 5: Background Refresh + Caching
**Goal:** Data refreshes automatically; app works fully offline.

- [ ] Implement WorkManager `PeriodicWorkRequest` for 15-min refresh
- [ ] Add configurable refresh interval (5/15/30/60 min / Manual)
- [ ] Show last-refreshed timestamp + freshness indicator dot
- [ ] Cache invalidation logic on indicator update
- [ ] Full offline mode with cached data + banner

**Duration:** ~2 days

---

### Milestone 6: AI Insights — Ollama
**Goal:** AI Insights panel queries local Ollama and displays a risk summary.

- [ ] Implement OllamaApiService
- [ ] Build AIRepository (Ollama path)
- [ ] Implement `GetAIAnalysisUseCase` with prompt construction
- [ ] Build AIInsightsScreen with "Analyze Now" button + result card
- [ ] Model selector (probe Ollama `/api/tags` on settings change)
- [ ] Loading state + error states
- [ ] Cache AI analysis result to Room

**Duration:** ~3 days

---

### Milestone 7: AI Insights — OpenAI
**Goal:** User can switch to OpenAI as an alternative AI provider.

- [ ] Implement OpenAIApiService
- [ ] Add OpenAI path to AIRepository
- [ ] Provider toggle in Settings (Ollama / OpenAI)
- [ ] OpenAI model selector (`gpt-4o-mini`, `gpt-4o`, `gpt-4-turbo`)
- [ ] OpenAI API key field (masked input, stored in DataStore)
- [ ] Provider badge in AI Insights result card

**Duration:** ~2 days

---

### Milestone 8: Polish + Non-Functional Requirements
**Goal:** App meets all NFRs and ships.

- [ ] Cold start optimization (< 3s to dashboard)
- [ ] Splash screen / loading shimmer for initial data load
- [ ] Empty states (no key, no network, no indicators)
- [ ] Dark mode audit — all components styled for both themes
- [ ] Settings persistence audit — all fields survive app restart
- [ ] FRED rate limit testing + backoff implementation
- [ ] ProGuard / R8 rules for release build
- [ ] v1 release build (debug APK for internal testing)

**Duration:** ~2–3 days

---

### Total Estimated Duration: ~15–20 days

### Phase Summary

| Phase | Milestones | Focus |
|---|---|---|
| Phase 1 | M1–M2 | Foundation + FRED data |
| Phase 2 | M3–M4 | Yahoo + horizon filtering |
| Phase 3 | M5 | Background work + offline |
| Phase 4 | M6–M7 | AI (Ollama + OpenAI) |
| Phase 5 | M8 | Polish + release |

---

*Document maintained by: Director*
*Next review: Before Phase 2 kickoff*
