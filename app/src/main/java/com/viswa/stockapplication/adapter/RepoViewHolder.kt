package com.viswa.stockapplication.adapter

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viswa.network.model.Repo
import com.viswa.stockapplication.R
import com.viswa.stockapplication.databinding.RepoListItemBinding

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RepoViewHolder(val binding: RepoListItemBinding, action : (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private var repo: Repo? = null

    init {
        binding.root.setOnClickListener {
            repo?.url?.let { url ->
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                it.context.startActivity(intent)
                action(url)
            }
        }
    }

    fun bind(repo: Repo?) {
        if (repo == null) {
            val resources = itemView.resources
            binding.repoName.text = resources.getString(R.string.loading)
            binding.repoDescription.visibility = View.GONE
            binding.repoLanguage.visibility = View.GONE
            binding.repoStars.text = resources.getString(R.string.unknown)
            binding.repoForks.text = resources.getString(R.string.unknown)
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Repo) {
        this.repo = repo
        binding.repoName.text = repo.fullName

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (repo.description != null) {
            binding.repoDescription.text = repo.description
            descriptionVisibility = View.VISIBLE
        }
        binding.repoDescription.visibility = descriptionVisibility

        binding.repoStars.text = repo.stars.toString()
        binding.repoForks.text = repo.forks.toString()

        // if the language is missing, hide the label and the value
        var languageVisibility = View.GONE
        if (!repo.language.isNullOrEmpty()) {
            val resources = this.itemView.context.resources
            binding.repoLanguage.text = resources.getString(R.string.language, repo.language)
            languageVisibility = View.VISIBLE
        }
        binding.repoLanguage.visibility = languageVisibility
    }

    companion object {
        fun create(parent: ViewGroup, action : (String) -> Unit): RepoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_item, parent, false)
            return RepoViewHolder(RepoListItemBinding.bind(view), action)
        }
    }
}