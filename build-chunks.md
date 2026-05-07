# MacroDash Build Chunks — Cron Tracker

Cron: Every 30 min, isolated agentTurn
Target: `~/Projects/macro-dash/`
Model: Qwenny (minimax-m2.7:cloud)
Fallback: ollama/deepseek-v4-pro:cloud

## Chunk Inventory

| Chunk | Milestone | Scope | Status |
|-------|-----------|-------|--------|
| C1 | M1 | Shell + Foundation — Gradle wrapper, dependencies, base UI scaffold, navigation, theme, Hilt setup | ✅ done |
| C2 | M2 | FRED Data Layer — FRED API client (Retrofit), Room entities, IndicatorRepository, 22 indicators with FRED series IDs | 🔴 pending |
| C3 | M3 | Yahoo Finance Data Layer — Yahoo Finance REST API (Retrofit), YahooIndicatorRepository, stock/market tickers | 🔴 pending |
| C4 | M4 | Risk Horizon Filter — toggle UI (Short/Mid/Long), filter logic, horizon-tagged indicator display | 🔴 pending |
| C5 | M5 | Background Refresh + Caching — WorkManager, 15-min auto-refresh, last-refresh timestamp, pull-to-refresh | 🔴 pending |
| C6 | M6 | AI Insights — Ollama — Ollama REST service, AIInsightsScreen, "Analyze Now" button, model selector, result card | 🔴 pending |
| C7 | M7 | AI Insights — OpenAI — OpenAI REST service, provider toggle in settings, OpenAI model selector, provider badge | 🔴 pending |
| C8 | M8 | Polish + NFRs — dark mode, error states, empty states, cold-start optimization, shimmer loading, release build | 🔴 pending |

## State File
`/home/user/.openclaw/workspace/tracking/state/macro-dash-build.json`

## Progress
- Last chunk run: none yet
- Next chunk: C1
- Completed: 0 / 8

## Notes
- Each chunk builds independently — no shared state between chunks except via `tracking/state/macro-dash-build.json`
- After each chunk completes, update status in this file and mark next chunk pending
- If a chunk fails, it stays at current status and retries next cron run
- Do NOT run multiple build chunks concurrently